package com.klef.eduplaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.Project;
import com.klef.eduplaty.entity.Team;
import com.klef.eduplaty.entity.User;

public interface TeamRepository extends JpaRepository<Team, Long>
{
    public List<Team> findByProject(Project project);
    public List<Team> findByCreatedBy(User createdBy);
}