package com.klef.eduplaty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.eduplaty.dto.CreateEvaluationDTO;
import com.klef.eduplaty.dto.EvaluationDTO;
import com.klef.eduplaty.dto.EvaluationDetailDTO;
import com.klef.eduplaty.entity.Evaluation;
import com.klef.eduplaty.entity.EvaluationCriteria;
import com.klef.eduplaty.entity.EvaluationDetail;
import com.klef.eduplaty.entity.Team;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.repository.EvaluationCriteriaRepository;
import com.klef.eduplaty.repository.EvaluationDetailRepository;
import com.klef.eduplaty.repository.EvaluationRepository;
import com.klef.eduplaty.repository.TeamRepository;
import com.klef.eduplaty.repository.UserRepository;

@Service
public class EvaluationServiceImpl implements EvaluationService
{
    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationDetailRepository evaluationDetailRepository;

    @Autowired
    private EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String evaluateTeam(CreateEvaluationDTO createEvaluationDTO)
    {
        Optional<Team> optionalTeam = teamRepository.findById(createEvaluationDTO.getTeamId());
        Optional<User> optionalTeacher = userRepository.findById(createEvaluationDTO.getEvaluatedById());

        if(optionalTeam.isEmpty())
        {
            return "Team not found";
        }

        if(optionalTeacher.isEmpty())
        {
            return "Teacher not found";
        }

        Evaluation evaluation = new Evaluation();
        evaluation.setTeam(optionalTeam.get());
        evaluation.setEvaluatedBy(optionalTeacher.get());
        evaluation.setFeedback(createEvaluationDTO.getFeedback());
        evaluation.setTotalMarks(0);

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        int total = 0;

        if(createEvaluationDTO.getDetails() != null)
        {
            for(EvaluationDetailDTO detailDTO : createEvaluationDTO.getDetails())
            {
                Optional<EvaluationCriteria> optionalCriteria = evaluationCriteriaRepository.findById(detailDTO.getCriteriaId());

                if(optionalCriteria.isPresent())
                {
                    EvaluationCriteria criteria = optionalCriteria.get();

                    EvaluationDetail detail = new EvaluationDetail();
                    detail.setEvaluation(savedEvaluation);
                    detail.setCriteria(criteria);
                    detail.setMarksObtained(detailDTO.getMarksObtained());
                    evaluationDetailRepository.save(detail);

                    total = total + detailDTO.getMarksObtained();
                }
            }
        }

        savedEvaluation.setTotalMarks(total);
        evaluationRepository.save(savedEvaluation);

        return "Evaluation submitted successfully";
    }

    @Override
    public EvaluationDTO getEvaluationById(Long id)
    {
        Optional<Evaluation> optionalEvaluation = evaluationRepository.findById(id);
        if(optionalEvaluation.isPresent())
        {
            return convertToDTO(optionalEvaluation.get());
        }
        return null;
    }

    @Override
    public List<EvaluationDTO> getEvaluationsByTeam(Long teamId)
    {
        List<EvaluationDTO> dtoList = new ArrayList<>();
        Optional<Team> optionalTeam = teamRepository.findById(teamId);

        if(optionalTeam.isPresent())
        {
            List<Evaluation> evaluations = evaluationRepository.findByTeam(optionalTeam.get());
            for(Evaluation evaluation : evaluations)
            {
                dtoList.add(convertToDTO(evaluation));
            }
        }

        return dtoList;
    }

    @Override
    public List<EvaluationDTO> getEvaluationsByTeacher(Long teacherId)
    {
        List<EvaluationDTO> dtoList = new ArrayList<>();
        Optional<User> optionalTeacher = userRepository.findById(teacherId);

        if(optionalTeacher.isPresent())
        {
            List<Evaluation> evaluations = evaluationRepository.findByEvaluatedBy(optionalTeacher.get());
            for(Evaluation evaluation : evaluations)
            {
                dtoList.add(convertToDTO(evaluation));
            }
        }

        return dtoList;
    }

    private EvaluationDTO convertToDTO(Evaluation evaluation)
    {
        EvaluationDTO dto = new EvaluationDTO();
        dto.setId(evaluation.getId());
        dto.setTeamId(evaluation.getTeam().getId());
        dto.setEvaluatedById(evaluation.getEvaluatedBy().getId());
        dto.setEvaluatedByName(evaluation.getEvaluatedBy().getName());
        dto.setTotalMarks(evaluation.getTotalMarks());
        dto.setFeedback(evaluation.getFeedback());

        List<EvaluationDetail> details = evaluationDetailRepository.findByEvaluation(evaluation);
        List<EvaluationDetailDTO> detailDTOList = new ArrayList<>();

        for(EvaluationDetail detail : details)
        {
            EvaluationDetailDTO detailDTO = new EvaluationDetailDTO();
            detailDTO.setCriteriaId(detail.getCriteria().getId());
            detailDTO.setCriteriaName(detail.getCriteria().getCriteriaName());
            detailDTO.setMaxMarks(detail.getCriteria().getMaxMarks());
            detailDTO.setMarksObtained(detail.getMarksObtained());
            detailDTOList.add(detailDTO);
        }

        dto.setDetails(detailDTOList);
        return dto;
    }
}