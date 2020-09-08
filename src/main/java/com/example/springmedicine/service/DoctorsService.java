package com.example.springmedicine.service;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.repository.DoctorsRepository;
import com.example.springmedicine.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

@Service
public class DoctorsService extends BaseService<DoctorsRepository, Doctors> {

    private final PatientsService service;

    public DoctorsService(DoctorsRepository repository, PatientsService service) {
        super(repository);
        this.service = service;
    }

    @Override
    public Doctors update(Doctors input) {

        Doctors doctor = getRepository().findByBaseId(input.getBaseId()).orElseThrow(() -> new ResourceNotFoundException(String.valueOf(input.getBaseId())));

        if (!Strings.isNullOrEmpty(input.getDoctorName())) {
            doctor.setDoctorName(input.getDoctorName());
        }
        if (!Strings.isNullOrEmpty(input.getPhone())) {
            doctor.setPhone(input.getPhone());
        }
        if (!Strings.isNullOrEmpty(input.getProfession())) {
            doctor.setProfession(input.getProfession());
        }
        if (!Strings.isNullOrEmpty(input.getRoom())) {
            doctor.setRoom(input.getRoom());
        }
        if (!Strings.isNullOrEmpty(input.getWorkPlace())) {
            doctor.setWorkPlace(input.getWorkPlace());
        }
        if (input.getStatus() != null) {
            doctor.setStatus(input.getStatus());
        }
        if (input.getPatientsNumber() != null) {
            doctor.setPatientsNumber(input.getPatientsNumber());
        }
        if (input.getExperience() != null) {
            doctor.setExperience(input.getExperience());
        }

        doctor.getPatients().addAll(input.getPatients());

        return getRepository().save(doctor);
    }

}
