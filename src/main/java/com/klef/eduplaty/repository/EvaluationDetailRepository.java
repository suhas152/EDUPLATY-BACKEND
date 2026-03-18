package com.klef.eduplaty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.Evaluation;
import com.klef.eduplaty.entity.EvaluationDetail;

public interface EvaluationDetailRepository extends JpaRepository<EvaluationDetail, Long>
{
    public List<EvaluationDetail> findByEvaluation(Evaluation evaluation);
}