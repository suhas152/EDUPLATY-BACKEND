package com.klef.eduplaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.EvaluationCriteria;
import com.klef.eduplaty.entity.EvaluationTemplate;

public interface EvaluationCriteriaRepository extends JpaRepository<EvaluationCriteria, Long>
{
    public List<EvaluationCriteria> findByTemplate(EvaluationTemplate template);
}