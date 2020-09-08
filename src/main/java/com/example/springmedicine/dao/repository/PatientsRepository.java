package com.example.springmedicine.dao.repository;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends BaseRepository<Patients> {

    List<Patients> findAllByDoctors(Doctors doctors);

}
