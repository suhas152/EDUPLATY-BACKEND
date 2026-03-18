package com.klef.eduplaty.service;

import java.util.List;

import com.klef.eduplaty.dto.ProjectDTO;

public interface ProjectService 
{
    public String createProject(ProjectDTO projectDTO);
    public List<ProjectDTO> getAllProjects();
    public ProjectDTO getProjectById(Long id);
    public List<ProjectDTO> getProjectsByMainTeacher(Long teacherId);
}