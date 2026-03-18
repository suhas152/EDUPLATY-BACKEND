package com.klef.eduplaty.dto;

import java.util.List;

public class TeamDTO 
{
    private Long id;
    private Long projectId;
    private String projectTitle;
    private Long createdById;
    private String createdByName;
    private String status;
    private List<Long> memberIds;
    private List<String> memberNames;
    private Long assignedTeacherId;
    private String assignedTeacherName;

    public TeamDTO() 
    {
    }

    public TeamDTO(Long id, Long projectId, String projectTitle, Long createdById, String createdByName,
                   String status, List<Long> memberIds, List<String> memberNames,
                   Long assignedTeacherId, String assignedTeacherName) 
    {
        this.id = id;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.createdById = createdById;
        this.createdByName = createdByName;
        this.status = status;
        this.memberIds = memberIds;
        this.memberNames = memberNames;
        this.assignedTeacherId = assignedTeacherId;
        this.assignedTeacherName = assignedTeacherName;
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

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public List<Long> getMemberIds() 
    {
        return memberIds;
    }

    public void setMemberIds(List<Long> memberIds) 
    {
        this.memberIds = memberIds;
    }

    public List<String> getMemberNames() 
    {
        return memberNames;
    }

    public void setMemberNames(List<String> memberNames) 
    {
        this.memberNames = memberNames;
    }

    public Long getAssignedTeacherId() 
    {
        return assignedTeacherId;
    }

    public void setAssignedTeacherId(Long assignedTeacherId) 
    {
        this.assignedTeacherId = assignedTeacherId;
    }

    public String getAssignedTeacherName() 
    {
        return assignedTeacherName;
    }

    public void setAssignedTeacherName(String assignedTeacherName) 
    {
        this.assignedTeacherName = assignedTeacherName;
    }
}