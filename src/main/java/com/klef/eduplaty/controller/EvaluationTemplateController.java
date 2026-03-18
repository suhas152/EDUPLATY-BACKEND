package com.klef.eduplaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.EvaluationCriteriaDTO;
import com.klef.eduplaty.dto.EvaluationTemplateDTO;
import com.klef.eduplaty.service.EvaluationTemplateService;

@RestController
@RequestMapping("/templates")
@CrossOrigin(origins = "*")
public class EvaluationTemplateController 
{
    @Autowired
    private EvaluationTemplateService templateService;

    @PostMapping
    public String createTemplate(@RequestBody EvaluationTemplateDTO dto)
    {
        return templateService.createEvaluationTemplate(dto);
    }

    @PostMapping("/{id}/criteria")
    public String addCriteria(@PathVariable Long id, @RequestBody EvaluationCriteriaDTO dto)
    {
        return templateService.addCriteriaToTemplate(id, dto);
    }

    @GetMapping("/{id}")
    public EvaluationTemplateDTO getTemplateById(@PathVariable Long id)
    {
        return templateService.getTemplateById(id);
    }

    @GetMapping("/project/{projectId}")
    public EvaluationTemplateDTO getTemplateByProject(@PathVariable Long projectId)
    {
        return templateService.getTemplateByProject(projectId);
    }

    @GetMapping("/{id}/criteria")
    public List<EvaluationCriteriaDTO> getCriteriaByTemplate(@PathVariable Long id)
    {
        return templateService.getCriteriaByTemplate(id);
    }
}