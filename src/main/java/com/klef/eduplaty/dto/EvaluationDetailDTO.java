package com.klef.eduplaty.dto;

public class EvaluationDetailDTO 
{
    private Long criteriaId;
    private String criteriaName;
    private int maxMarks;
    private int marksObtained;

    public EvaluationDetailDTO() 
    {
    }

    public EvaluationDetailDTO(Long criteriaId, String criteriaName, int maxMarks, int marksObtained) 
    {
        this.criteriaId = criteriaId;
        this.criteriaName = criteriaName;
        this.maxMarks = maxMarks;
        this.marksObtained = marksObtained;
    }

    public Long getCriteriaId() 
    {
        return criteriaId;
    }

    public void setCriteriaId(Long criteriaId) 
    {
        this.criteriaId = criteriaId;
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

    public int getMarksObtained() 
    {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) 
    {
        this.marksObtained = marksObtained;
    }
}