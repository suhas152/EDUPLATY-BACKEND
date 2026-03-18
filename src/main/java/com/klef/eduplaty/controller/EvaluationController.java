package com.klef.eduplaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.CreateEvaluationDTO;
import com.klef.eduplaty.dto.EvaluationDTO;
import com.klef.eduplaty.service.EvaluationService;

@RestController
@RequestMapping("/evaluations")
@CrossOrigin(origins = "*")
public class EvaluationController 
{
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public String evaluateTeam(@RequestBody CreateEvaluationDTO dto)
    {
        return evaluationService.evaluateTeam(dto);
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluationById(@PathVariable Long id)
    {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<EvaluationDTO> getEvaluationsByTeam(@PathVariable Long teamId)
    {
        return evaluationService.getEvaluationsByTeam(teamId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<EvaluationDTO> getEvaluationsByTeacher(@PathVariable Long teacherId)
    {
        return evaluationService.getEvaluationsByTeacher(teacherId);
    }
}