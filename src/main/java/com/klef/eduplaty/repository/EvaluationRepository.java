package com.klef.eduplaty.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.eduplaty.entity.Evaluation;
import com.klef.eduplaty.entity.Team;
import com.klef.eduplaty.entity.User;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long>
{
    public List<Evaluation> findByTeam(Team team);
    public List<Evaluation> findByEvaluatedBy(User evaluatedBy);
    public Optional<Evaluation> findByTeamAndEvaluatedBy(Team team, User evaluatedBy);
}