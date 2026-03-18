package com.klef.eduplaty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.eduplaty.dto.EvaluationCriteriaDTO;
import com.klef.eduplaty.dto.EvaluationTemplateDTO;
import com.klef.eduplaty.entity.EvaluationCriteria;
import com.klef.eduplaty.entity.EvaluationTemplate;
import com.klef.eduplaty.entity.Project;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.repository.EvaluationCriteriaRepository;
import com.klef.eduplaty.repository.EvaluationTemplateRepository;
import com.klef.eduplaty.repository.ProjectRepository;
import com.klef.eduplaty.repository.UserRepository;

@Service
public class EvaluationTemplateServiceImpl implements EvaluationTemplateService
{
    @Autowired
    private EvaluationTemplateRepository evaluationTemplateRepository;

    @Autowired
    private EvaluationCriteriaRepository evaluationCriteriaRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createEvaluationTemplate(EvaluationTemplateDTO evaluationTemplateDTO)
    {
        Optional<Project> optionalProject = projectRepository.findById(evaluationTemplateDTO.getProjectId());
        Optional<User> optionalUser = userRepository.findById(evaluationTemplateDTO.getCreatedById());

        if(optionalProject.isEmpty())
        {
            return "Project not found";
        }

        if(optionalUser.isEmpty())
        {
            return "Main teacher not found";
        }

        Optional<EvaluationTemplate> existingTemplate = evaluationTemplateRepository.findByProject(optionalProject.get());
        if(existingTemplate.isPresent())
        {
            return "Template already exists for this project";
        }

        EvaluationTemplate template = new EvaluationTemplate();
        template.setProject(optionalProject.get());
        template.setCreatedBy(optionalUser.get());

        evaluationTemplateRepository.save(template);
        return "Evaluation template created successfully";
    }

    @Override
    public String addCriteriaToTemplate(Long templateId, EvaluationCriteriaDTO criteriaDTO)
    {
        Optional<EvaluationTemplate> optionalTemplate = evaluationTemplateRepository.findById(templateId);
        if(optionalTemplate.isEmpty())
        {
            return "Template not found";
        }

        EvaluationCriteria criteria = new EvaluationCriteria();
        criteria.setCriteriaName(criteriaDTO.getCriteriaName());
        criteria.setMaxMarks(criteriaDTO.getMaxMarks());
        criteria.setTemplate(optionalTemplate.get());

        evaluationCriteriaRepository.save(criteria);
        return "Criteria added successfully";
    }

    @Override
    public EvaluationTemplateDTO getTemplateById(Long id)
    {
        Optional<EvaluationTemplate> optionalTemplate = evaluationTemplateRepository.findById(id);
        if(optionalTemplate.isPresent())
        {
            return convertToDTO(optionalTemplate.get());
        }
        return null;
    }

    @Override
    public EvaluationTemplateDTO getTemplateByProject(Long projectId)
    {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isPresent())
        {
            Optional<EvaluationTemplate> optionalTemplate = evaluationTemplateRepository.findByProject(optionalProject.get());
            if(optionalTemplate.isPresent())
            {
                return convertToDTO(optionalTemplate.get());
            }
        }
        return null;
    }

    @Override
    public List<EvaluationCriteriaDTO> getCriteriaByTemplate(Long templateId)
    {
        List<EvaluationCriteriaDTO> dtoList = new ArrayList<>();
        Optional<EvaluationTemplate> optionalTemplate = evaluationTemplateRepository.findById(templateId);

        if(optionalTemplate.isPresent())
        {
            List<EvaluationCriteria> criteriaList = evaluationCriteriaRepository.findByTemplate(optionalTemplate.get());
            for(EvaluationCriteria criteria : criteriaList)
            {
                EvaluationCriteriaDTO dto = new EvaluationCriteriaDTO();
                dto.setId(criteria.getId());
                dto.setCriteriaName(criteria.getCriteriaName());
                dto.setMaxMarks(criteria.getMaxMarks());
                dtoList.add(dto);
            }
        }

        return dtoList;
    }

    private EvaluationTemplateDTO convertToDTO(EvaluationTemplate template)
    {
        EvaluationTemplateDTO dto = new EvaluationTemplateDTO();
        dto.setId(template.getId());
        dto.setProjectId(template.getProject().getId());
        dto.setProjectTitle(template.getProject().getTitle());
        dto.setCreatedById(template.getCreatedBy().getId());
        dto.setCreatedByName(template.getCreatedBy().getName());

        List<EvaluationCriteria> criteriaList = evaluationCriteriaRepository.findByTemplate(template);
        List<EvaluationCriteriaDTO> criteriaDTOList = new ArrayList<>();

        for(EvaluationCriteria criteria : criteriaList)
        {
            EvaluationCriteriaDTO criteriaDTO = new EvaluationCriteriaDTO();
            criteriaDTO.setId(criteria.getId());
            criteriaDTO.setCriteriaName(criteria.getCriteriaName());
            criteriaDTO.setMaxMarks(criteria.getMaxMarks());
            criteriaDTOList.add(criteriaDTO);
        }

        dto.setCriteriaList(criteriaDTOList);
        return dto;
    }
}