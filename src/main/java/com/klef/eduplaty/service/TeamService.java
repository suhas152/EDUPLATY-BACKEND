package com.klef.eduplaty.service;

import java.util.List;

import com.klef.eduplaty.dto.CreateTeamDTO;
import com.klef.eduplaty.dto.TeamDTO;

public interface TeamService 
{
    public String createTeam(CreateTeamDTO createTeamDTO);
    public String joinTeam(Long teamId, Long studentId);
    public List<TeamDTO> getAllTeams();
    public TeamDTO getTeamById(Long id);
    public List<TeamDTO> getTeamsByProject(Long projectId);
    public List<TeamDTO> getTeamsByStudent(Long studentId);
    public String assignTeacher(Long teamId, Long teacherId);
    public String removeMember(Long teamId, Long studentId);
}