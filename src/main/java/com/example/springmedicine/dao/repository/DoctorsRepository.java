package com.example.springmedicine.dao.repository;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends BaseRepository<Doctors> {
}
