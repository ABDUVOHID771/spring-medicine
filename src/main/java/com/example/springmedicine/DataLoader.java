package com.example.springmedicine;

import com.example.springmedicine.dao.domain.BloodType;
import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.domain.Status;
import com.example.springmedicine.service.DoctorsService;
import com.example.springmedicine.service.PatientsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final DoctorsService doctorsService;
    private final PatientsService patientsService;

    public DataLoader(DoctorsService doctorsService, PatientsService patientsService) {
        this.doctorsService = doctorsService;
        this.patientsService = patientsService;
    }

    @Override
    public void run(String... args) {
        loader();
    }

    private void loader() {

        Doctors doctor = new Doctors();
        doctor.setExperience((long) 3);
        doctor.setPatientsNumber((long) 5);
        doctor.setStatus(Status.MIDDLE);
        doctor.setWorkPlace("UCHTEPA");
        doctor.setRoom("A14");
        doctor.setProfession("Dentist");
        doctor.setDoctorName("Alish");
        doctor.setPhone("1234567");
        doctorsService.create(doctor);

        Patients patient = new Patients();
        patient.setStomachRound(40.4);
        patient.setPressure(90.3);
        patient.setComingDate(Instant.now());
        patient.setBloodType(BloodType.SORT_1);
        patient.setAge(22);
        patient.setComplaints("Stomachache");
        patient.setPatientName("Anvar");
        patientsService.create(patient);

        doctor.addPatients(Set.of(patient));
        doctorsService.update(doctor);

        Patients patient1 = new Patients();
        patient1.setStomachRound(40.4);
        patient1.setPressure(90.3);
        patient1.setComingDate(Instant.now());
        patient1.setBloodType(BloodType.SORT_1);
        patient1.setAge(22);
        patient1.setComplaints("Stomachache");
        patient1.setPatientName("Anvar1");
        patientsService.create(patient1);

        doctor.addPatients(Set.of(patient1));
        doctorsService.update(doctor);


    }

}
