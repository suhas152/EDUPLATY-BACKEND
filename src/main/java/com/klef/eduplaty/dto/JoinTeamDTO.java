package com.klef.eduplaty.dto;

public class JoinTeamDTO 
{
    private Long teamId;
    private Long studentId;

    public JoinTeamDTO() 
    {
    }

    public JoinTeamDTO(Long teamId, Long studentId) 
    {
        this.teamId = teamId;
        this.studentId = studentId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }

    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }
}