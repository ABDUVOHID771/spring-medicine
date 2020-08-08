package com.example.springmedicine.dao.repository.base;

import com.example.springmedicine.dao.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {

    Optional<E> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

}
