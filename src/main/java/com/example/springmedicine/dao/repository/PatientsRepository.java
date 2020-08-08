package com.example.springmedicine.dao.repository;

import com.example.springmedicine.dao.domain.Patients;
import com.example.springmedicine.dao.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends BaseRepository<Patients> {
}
