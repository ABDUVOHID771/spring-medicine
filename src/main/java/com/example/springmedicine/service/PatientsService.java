package com.example.springmedicine.service;

import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.repository.PatientsRepository;
import com.example.springmedicine.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

@Service
public class PatientsService extends BaseService<PatientsRepository, Patients> {

    public PatientsService(PatientsRepository repository) {
        super(repository);
    }

    @Override
    public Patients update(Patients input) {

        Patients patient = getRepository().findByUuid(input.getUuid()).orElseThrow(() -> new ResourceNotFoundException(input.getUuid()));

        if (!Strings.isNullOrEmpty(input.getComplaints())) {
            patient.setComplaints(input.getComplaints());
        }
        if (!Strings.isNullOrEmpty(input.getPatientName())) {
            patient.setPatientName(input.getPatientName());
        }
        if (input.getAge() != null) {
            patient.setAge(input.getAge());
        }
        if (input.getBloodType() != null) {
            patient.setBloodType(input.getBloodType());
        }
        if (input.getComingDate() != null) {
            patient.setComingDate(input.getComingDate());
        }
        if (input.getPressure() != null) {
            patient.setPressure(input.getPressure());
        }
        if (input.getStomachRound() != null) {
            patient.setStomachRound(input.getStomachRound());
        }

        patient.getDoctors().addAll(input.getDoctors());

        return getRepository().save(patient);
    }

}
