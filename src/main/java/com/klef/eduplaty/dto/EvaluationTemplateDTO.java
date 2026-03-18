package com.klef.eduplaty.dto;

import java.util.List;

public class EvaluationTemplateDTO 
{
    private Long id;
    private Long projectId;
    private String projectTitle;
    private Long createdById;
    private String createdByName;
    private List<EvaluationCriteriaDTO> criteriaList;

    public EvaluationTemplateDTO() 
    {
    }

    public EvaluationTemplateDTO(Long id, Long projectId, String projectTitle, Long createdById, String createdByName, List<EvaluationCriteriaDTO> criteriaList) 
    {
        this.id = id;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.criteriaList = criteriaList;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public String getProjectTitle() 
    {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) 
    {
        this.projectTitle = projectTitle;
    }

    public Long getCreatedById() 
    {
        return createdById;
    }

    public void setCreatedById(Long createdById) 
    {
        this.createdById = createdById;
    }

    public String getCreatedByName() 
    {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) 
    {
        this.createdByName = createdByName;
    }

    public List<EvaluationCriteriaDTO> getCriteriaList() 
    {
        return criteriaList;
    }

    public void setCriteriaList(List<EvaluationCriteriaDTO> criteriaList) 
    {
        this.criteriaList = criteriaList;
    }
}