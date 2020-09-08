package com.example.springmedicine.dao.repository.base;

import com.example.springmedicine.dao.domain.Doctors;
import com.example.springmedicine.dao.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    Optional<E> findByBaseId(Long baseId);

    void deleteByBaseId(Long baseId);

}
