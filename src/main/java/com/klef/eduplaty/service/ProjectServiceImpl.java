package com.klef.eduplaty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.eduplaty.dto.ProjectDTO;
import com.klef.eduplaty.entity.Project;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.repository.ProjectRepository;
import com.klef.eduplaty.repository.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService
{
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createProject(ProjectDTO projectDTO)
    {
        Optional<User> optionalUser = userRepository.findById(projectDTO.getCreatedById());
        if(optionalUser.isEmpty())
        {
            return "Main teacher not found";
        }

        User teacher = optionalUser.get();

        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setMaxTeamSize(projectDTO.getMaxTeamSize());
        project.setDeadline(projectDTO.getDeadline());
        project.setCreatedBy(teacher);

        projectRepository.save(project);
        return "Project created successfully";
    }

    @Override
    public List<ProjectDTO> getAllProjects()
    {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> dtoList = new ArrayList<>();

        for(Project project : projects)
        {
            ProjectDTO dto = new ProjectDTO();
            dto.setId(project.getId());
            dto.setTitle(project.getTitle());
            dto.setDescription(project.getDescription());
            dto.setMaxTeamSize(project.getMaxTeamSize());
            dto.setDeadline(project.getDeadline());
            dto.setCreatedById(project.getCreatedBy().getId());
            dto.setCreatedByName(project.getCreatedBy().getName());
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public ProjectDTO getProjectById(Long id)
    {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent())
        {
            Project project = optionalProject.get();

            ProjectDTO dto = new ProjectDTO();
            dto.setId(project.getId());
            dto.setTitle(project.getTitle());
            dto.setDescription(project.getDescription());
            dto.setMaxTeamSize(project.getMaxTeamSize());
            dto.setDeadline(project.getDeadline());
            dto.setCreatedById(project.getCreatedBy().getId());
            dto.setCreatedByName(project.getCreatedBy().getName());

            return dto;
        }
        return null;
    }

    @Override
    public List<ProjectDTO> getProjectsByMainTeacher(Long teacherId)
    {
        List<ProjectDTO> dtoList = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(teacherId);

        if(optionalUser.isPresent())
        {
            List<Project> projects = projectRepository.findByCreatedBy(optionalUser.get());

            for(Project project : projects)
            {
                ProjectDTO dto = new ProjectDTO();
                dto.setId(project.getId());
                dto.setTitle(project.getTitle());
                dto.setDescription(project.getDescription());
                dto.setMaxTeamSize(project.getMaxTeamSize());
                dto.setDeadline(project.getDeadline());
                dto.setCreatedById(project.getCreatedBy().getId());
                dto.setCreatedByName(project.getCreatedBy().getName());
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}