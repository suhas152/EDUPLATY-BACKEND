package com.klef.eduplaty.service;

import java.util.List;

import com.klef.eduplaty.dto.EvaluationCriteriaDTO;
import com.klef.eduplaty.dto.EvaluationTemplateDTO;

public interface EvaluationTemplateService 
{
    public String createEvaluationTemplate(EvaluationTemplateDTO evaluationTemplateDTO);
    public String addCriteriaToTemplate(Long templateId, EvaluationCriteriaDTO criteriaDTO);
    public EvaluationTemplateDTO getTemplateById(Long id);
    public EvaluationTemplateDTO getTemplateByProject(Long projectId);
    public List<EvaluationCriteriaDTO> getCriteriaByTemplate(Long templateId);
}