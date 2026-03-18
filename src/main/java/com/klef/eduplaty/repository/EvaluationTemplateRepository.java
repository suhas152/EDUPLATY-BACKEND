package com.klef.eduplaty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.EvaluationTemplate;
import com.klef.eduplaty.entity.Project;

public interface EvaluationTemplateRepository extends JpaRepository<EvaluationTemplate, Long>
{
    public Optional<EvaluationTemplate> findByProject(Project project);
}