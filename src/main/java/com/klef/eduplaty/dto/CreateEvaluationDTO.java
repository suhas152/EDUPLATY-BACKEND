package com.klef.eduplaty.dto;

import java.util.List;

public class CreateEvaluationDTO 
{
    private Long teamId;
    private Long evaluatedById;
    private String feedback;
    private List<EvaluationDetailDTO> details;

    public CreateEvaluationDTO() 
    {
    }

    public CreateEvaluationDTO(Long teamId, Long evaluatedById, String feedback, List<EvaluationDetailDTO> details) 
    {
        this.teamId = teamId;
        this.evaluatedById = evaluatedById;
        this.feedback = feedback;
        this.details = details;
    }

    public Long getTeamId() 
    {
        return teamId;
    }

    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getEvaluatedById() 
    {
        return evaluatedById;
    }

    public void setEvaluatedById(Long evaluatedById) 
    {
        this.evaluatedById = evaluatedById;
    }

    public String getFeedback() 
    {
        return feedback;
    }

    public void setFeedback(String feedback) 
    {
        this.feedback = feedback;
    }

    public List<EvaluationDetailDTO> getDetails() 
    {
        return details;
    }

    public void setDetails(List<EvaluationDetailDTO> details) 
    {
        this.details = details;
    }
}