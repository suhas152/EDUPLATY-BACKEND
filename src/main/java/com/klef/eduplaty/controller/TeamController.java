package com.klef.eduplaty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.eduplaty.dto.CreateTeamDTO;
import com.klef.eduplaty.dto.TeamDTO;
import com.klef.eduplaty.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController 
{
    @Autowired
    private TeamService teamService;

    @PostMapping
    public String createTeam(@RequestBody CreateTeamDTO dto)
    {
        return teamService.createTeam(dto);
    }

    @PostMapping("/join")
    public String joinTeam(@RequestParam Long teamId, @RequestParam Long studentId)
    {
        return teamService.joinTeam(teamId, studentId);
    }

    @GetMapping
    public List<TeamDTO> getAllTeams()
    {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamDTO getTeamById(@PathVariable Long id)
    {
        return teamService.getTeamById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<TeamDTO> getTeamsByProject(@PathVariable Long projectId)
    {
        return teamService.getTeamsByProject(projectId);
    }

    @GetMapping("/student/{studentId}")
    public List<TeamDTO> getTeamsByStudent(@PathVariable Long studentId)
    {
        return teamService.getTeamsByStudent(studentId);
    }

    @PostMapping("/assign-teacher")
    public String assignTeacher(@RequestParam Long teamId, @RequestParam Long teacherId)
    {
        return teamService.assignTeacher(teamId, teacherId);
    }

    @DeleteMapping("/remove-member")
    public String removeMember(@RequestParam Long teamId, @RequestParam Long studentId)
    {
        return teamService.removeMember(teamId, studentId);
    }
}