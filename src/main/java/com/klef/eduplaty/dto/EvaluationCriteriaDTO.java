package com.klef.eduplaty.dto;

public class EvaluationCriteriaDTO 
{
    private Long id;
    private String criteriaName;
    private int maxMarks;

    public EvaluationCriteriaDTO() 
    {
    }

    public EvaluationCriteriaDTO(Long id, String criteriaName, int maxMarks) 
    {
        this.id = id;
        this.criteriaName = criteriaName;
        this.maxMarks = maxMarks;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getCriteriaName() 
    {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) 
    {
        this.criteriaName = criteriaName;
    }

    public int getMaxMarks() 
    {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) 
    {
        this.maxMarks = maxMarks;
    }
}