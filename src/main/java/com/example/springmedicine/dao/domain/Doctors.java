package com.example.springmedicine.dao.domain;

import com.example.springmedicine.dao.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctors extends BaseEntity {

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = Patients.class)
    @JoinTable(
            name = "doctors_patients",
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    )
    @JsonIgnore
    Set<Patients> patients = new HashSet<>();

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "work_place")
    private String workPlace;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profession")
    private String profession;

    @Column(name = "status")
    private Status status;

    @Column(name = "patients_number")
    private Long patientsNumber;

    @Column(name = "experience")
    private Long experience;

    @Column(name = "room")
    private String room;

    public void addPatients(Set<Patients> addNewPatients) {
        if (addNewPatients != null) {
            patients.addAll(addNewPatients);
        }
    }

    @Column(unique = true, nullable = false, updatable = false)
    private Long baseId;

    @Transient
    private static Long sequence = 1555L;

    @PrePersist
    public void generateBaseId() {
        this.baseId = sequence;
        ++sequence;
    }


}
