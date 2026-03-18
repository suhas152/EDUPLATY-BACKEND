package com.klef.eduplaty.dto;

import java.time.LocalDate;

public class ProjectDTO 
{
    private Long id;
    private String title;
    private String description;
    private int maxTeamSize;
    private LocalDate deadline;
    private Long createdById;
    private String createdByName;

    public ProjectDTO() 
    {
    }

    public ProjectDTO(Long id, String title, String description, int maxTeamSize, LocalDate deadline, Long createdById, String createdByName) 
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxTeamSize = maxTeamSize;
        this.deadline = deadline;
        this.createdById = createdById;
        this.createdByName = createdByName;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public int getMaxTeamSize() 
    {
        return maxTeamSize;
    }

    public void setMaxTeamSize(int maxTeamSize) 
    {
        this.maxTeamSize = maxTeamSize;
    }

    public LocalDate getDeadline() 
    {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) 
    {
        this.deadline = deadline;
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
}