package com.klef.eduplaty.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluation_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "criteria_id")
    private EvaluationCriteria criteria;

    private int marksObtained;
}