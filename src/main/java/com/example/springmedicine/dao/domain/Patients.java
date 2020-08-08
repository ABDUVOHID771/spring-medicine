package com.example.springmedicine.dao.domain;

import com.example.springmedicine.dao.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patients extends BaseEntity {

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "patients", targetEntity = Doctors.class)
    Set<Doctors> doctors = new HashSet<>();

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "coming_date")
    private Instant comingDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "stomach_round")
    private Double stomachRound;

    @Column(name = "blood_type")
    private BloodType bloodType;

    @Column(name = "complaints")
    private String complaints;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "result_analysis")
    private String resultAnalysis;

}
