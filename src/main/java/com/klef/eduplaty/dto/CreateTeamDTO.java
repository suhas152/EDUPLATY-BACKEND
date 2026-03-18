package com.klef.eduplaty.dto;

import java.util.List;

public class CreateTeamDTO 
{
    private Long projectId;
    private Long createdById;
    private List<Long> memberIds;

    public CreateTeamDTO() 
    {
    }

    public CreateTeamDTO(Long projectId, Long createdById, List<Long> memberIds) 
    {
        this.projectId = projectId;
        this.createdById = createdById;
        this.memberIds = memberIds;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getCreatedById() 
    {
        return createdById;
    }

    public void setCreatedById(Long createdById) 
    {
        this.createdById = createdById;
    }

    public List<Long> getMemberIds() 
    {
        return memberIds;
    }

    public void setMemberIds(List<Long> memberIds) 
    {
        this.memberIds = memberIds;
    }
}