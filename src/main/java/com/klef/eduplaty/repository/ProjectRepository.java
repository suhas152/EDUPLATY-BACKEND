package com.klef.eduplaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.Project;
import com.klef.eduplaty.entity.User;

public interface ProjectRepository extends JpaRepository<Project, Long>
{
    public List<Project> findByCreatedBy(User createdBy);
}