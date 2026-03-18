package com.klef.eduplaty.dto;

import java.util.List;

public class EvaluationDTO 
{
    private Long id;
    private Long teamId;
    private Long evaluatedById;
    private String evaluatedByName;
    private int totalMarks;
    private String feedback;
    private List<EvaluationDetailDTO> details;

    public EvaluationDTO() 
    {
    }

    public EvaluationDTO(Long id, Long teamId, Long evaluatedById, String evaluatedByName, int totalMarks, String feedback, List<EvaluationDetailDTO> details) 
    {
        this.id = id;
        this.teamId = teamId;
        this.evaluatedById = evaluatedById;
        this.evaluatedByName = evaluatedByName;
        this.totalMarks = totalMarks;
        this.feedback = feedback;
        this.details = details;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
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

    public String getEvaluatedByName() 
    {
        return evaluatedByName;
    }

    public void setEvaluatedByName(String evaluatedByName) 
    {
        this.evaluatedByName = evaluatedByName;
    }

    public int getTotalMarks() 
    {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) 
    {
        this.totalMarks = totalMarks;
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