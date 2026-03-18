package com.klef.eduplaty.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluation_criteria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String criteriaName;
    private int maxMarks;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private EvaluationTemplate template;
}