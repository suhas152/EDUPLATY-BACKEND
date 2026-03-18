package com.klef.eduplaty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.eduplaty.dto.CreateTeamDTO;
import com.klef.eduplaty.dto.TeamDTO;
import com.klef.eduplaty.entity.Project;
import com.klef.eduplaty.entity.Team;
import com.klef.eduplaty.entity.TeamMember;
import com.klef.eduplaty.entity.User;
import com.klef.eduplaty.repository.ProjectRepository;
import com.klef.eduplaty.repository.TeamMemberRepository;
import com.klef.eduplaty.repository.TeamRepository;
import com.klef.eduplaty.repository.UserRepository;

@Service
public class TeamServiceImpl implements TeamService
{
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createTeam(CreateTeamDTO createTeamDTO)
    {
        Optional<Project> optionalProject = projectRepository.findById(createTeamDTO.getProjectId());
        Optional<User> optionalUser = userRepository.findById(createTeamDTO.getCreatedById());

        if(optionalProject.isEmpty())
        {
            return "Project not found";
        }

        if(optionalUser.isEmpty())
        {
            return "Student not found";
        }

        Project project = optionalProject.get();
        User creator = optionalUser.get();

        Team team = new Team();
        team.setProject(project);
        team.setCreatedBy(creator);
        team.setStatus("OPEN");

        Team savedTeam = teamRepository.save(team);

        TeamMember creatorMember = new TeamMember();
        creatorMember.setTeam(savedTeam);
        creatorMember.setStudent(creator);
        teamMemberRepository.save(creatorMember);

        if(createTeamDTO.getMemberIds() != null)
        {
            for(Long memberId : createTeamDTO.getMemberIds())
            {
                Optional<User> memberOptional = userRepository.findById(memberId);
                if(memberOptional.isPresent() && !memberId.equals(creator.getId()))
                {
                    TeamMember existing = teamMemberRepository.findByTeamAndStudent(savedTeam, memberOptional.get()).orElse(null);
                    if(existing == null)
                    {
                        TeamMember member = new TeamMember();
                        member.setTeam(savedTeam);
                        member.setStudent(memberOptional.get());
                        teamMemberRepository.save(member);
                    }
                }
            }
        }

        int memberCount = teamMemberRepository.findByTeam(savedTeam).size();
        if(memberCount >= project.getMaxTeamSize())
        {
            savedTeam.setStatus("FULL");
            teamRepository.save(savedTeam);
        }

        return "Team created successfully";
    }

    @Override
    public String joinTeam(Long teamId, Long studentId)
    {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalUser = userRepository.findById(studentId);

        if(optionalTeam.isEmpty())
        {
            return "Team not found";
        }

        if(optionalUser.isEmpty())
        {
            return "Student not found";
        }

        Team team = optionalTeam.get();
        User student = optionalUser.get();

        Optional<TeamMember> existingMember = teamMemberRepository.findByTeamAndStudent(team, student);
        if(existingMember.isPresent())
        {
            return "Student already in team";
        }

        List<TeamMember> members = teamMemberRepository.findByTeam(team);
        if(members.size() >= team.getProject().getMaxTeamSize())
        {
            return "Team is already full";
        }

        TeamMember member = new TeamMember();
        member.setTeam(team);
        member.setStudent(student);
        teamMemberRepository.save(member);

        members = teamMemberRepository.findByTeam(team);
        if(members.size() >= team.getProject().getMaxTeamSize())
        {
            team.setStatus("FULL");
            teamRepository.save(team);
        }

        return "Joined team successfully";
    }

    @Override
    public List<TeamDTO> getAllTeams()
    {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> dtoList = new ArrayList<>();

        for(Team team : teams)
        {
            dtoList.add(convertToDTO(team));
        }

        return dtoList;
    }

    @Override
    public TeamDTO getTeamById(Long id)
    {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent())
        {
            return convertToDTO(optionalTeam.get());
        }
        return null;
    }

    @Override
    public List<TeamDTO> getTeamsByProject(Long projectId)
    {
        List<TeamDTO> dtoList = new ArrayList<>();
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if(optionalProject.isPresent())
        {
            List<Team> teams = teamRepository.findByProject(optionalProject.get());
            for(Team team : teams)
            {
                dtoList.add(convertToDTO(team));
            }
        }

        return dtoList;
    }

    @Override
    public List<TeamDTO> getTeamsByStudent(Long studentId)
    {
        List<TeamDTO> dtoList = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(studentId);

        if(optionalUser.isPresent())
        {
            List<TeamMember> memberships = teamMemberRepository.findByStudent(optionalUser.get());
            for(TeamMember teamMember : memberships)
            {
                dtoList.add(convertToDTO(teamMember.getTeam()));
            }
        }

        return dtoList;
    }

    @Override
    public String assignTeacher(Long teamId, Long teacherId)
    {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalTeacher = userRepository.findById(teacherId);

        if(optionalTeam.isEmpty())
        {
            return "Team not found";
        }

        if(optionalTeacher.isEmpty())
        {
            return "Teacher not found";
        }

        if(optionalTeacher.get().getRole() != User.Role.TEACHER)
        {
            return "Selected user is not a teacher";
        }

        Team team = optionalTeam.get();
        team.setAssignedTeacher(optionalTeacher.get());
        teamRepository.save(team);

        return "Teacher assigned successfully";
    }

    @Override
    public String removeMember(Long teamId, Long studentId)
    {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        Optional<User> optionalStudent = userRepository.findById(studentId);

        if(optionalTeam.isEmpty())
        {
            return "Team not found";
        }

        if(optionalStudent.isEmpty())
        {
            return "Student not found";
        }

        Optional<TeamMember> optionalMember = teamMemberRepository.findByTeamAndStudent(optionalTeam.get(), optionalStudent.get());

        if(optionalMember.isEmpty())
        {
            return "Student is not part of this team";
        }

        teamMemberRepository.delete(optionalMember.get());

        Team team = optionalTeam.get();
        int count = teamMemberRepository.findByTeam(team).size();
        if(count < team.getProject().getMaxTeamSize())
        {
            team.setStatus("OPEN");
            teamRepository.save(team);
        }

        return "Member removed successfully";
    }

    private TeamDTO convertToDTO(Team team)
    {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setProjectId(team.getProject().getId());
        dto.setProjectTitle(team.getProject().getTitle());
        dto.setCreatedById(team.getCreatedBy().getId());
        dto.setCreatedByName(team.getCreatedBy().getName());
        dto.setStatus(team.getStatus());

        if(team.getAssignedTeacher() != null)
        {
            dto.setAssignedTeacherId(team.getAssignedTeacher().getId());
            dto.setAssignedTeacherName(team.getAssignedTeacher().getName());
        }

        List<TeamMember> members = teamMemberRepository.findByTeam(team);
        List<Long> memberIds = new ArrayList<>();
        List<String> memberNames = new ArrayList<>();

        for(TeamMember member : members)
        {
            memberIds.add(member.getStudent().getId());
            memberNames.add(member.getStudent().getName());
        }

        dto.setMemberIds(memberIds);
        dto.setMemberNames(memberNames);

        return dto;
    }
}