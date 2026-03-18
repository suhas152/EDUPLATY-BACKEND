package com.klef.eduplaty.service;

import java.util.List;

import com.klef.eduplaty.dto.CreateEvaluationDTO;
import com.klef.eduplaty.dto.EvaluationDTO;

public interface EvaluationService 
{
    public String evaluateTeam(CreateEvaluationDTO createEvaluationDTO);
    public EvaluationDTO getEvaluationById(Long id);
    public List<EvaluationDTO> getEvaluationsByTeam(Long teamId);
    public List<EvaluationDTO> getEvaluationsByTeacher(Long teacherId);
}