package com.klef.eduplaty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.Team;
import com.klef.eduplaty.entity.TeamMember;
import com.klef.eduplaty.entity.User;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>
{
    public List<TeamMember> findByTeam(Team team);
    public List<TeamMember> findByStudent(User student);
    public Optional<TeamMember> findByTeamAndStudent(Team team, User student);
}