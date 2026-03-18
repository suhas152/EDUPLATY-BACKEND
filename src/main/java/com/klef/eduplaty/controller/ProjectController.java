package com.klef.eduplaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.ProjectDTO;
import com.klef.eduplaty.service.ProjectService;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController 
{
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public String createProject(@RequestBody ProjectDTO dto)
    {
        return projectService.createProject(dto);
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects()
    {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id)
    {
        return projectService.getProjectById(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ProjectDTO> getProjectsByTeacher(@PathVariable Long teacherId)
    {
        return projectService.getProjectsByMainTeacher(teacherId);
    }
}