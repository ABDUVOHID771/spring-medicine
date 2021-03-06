package com.example.springmedicine.service;

import com.example.springmedicine.dao.domain.base.BaseEntity;
import com.example.springmedicine.dao.repository.base.BaseRepository;
import com.example.springmedicine.exception.ResourceNotFoundException;
import lombok.Getter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
public abstract class BaseService<R extends BaseRepository<E>, E extends BaseEntity> {

    private final R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }

    public E create(E input) {
        return repository.save(input);
    }

    public E get(Long baseId) {
        Optional<E> entity = repository.findByBaseId(baseId);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(baseId.toString());
        }
        return entity.get();
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public abstract E update(E input);

    @Transactional
    public void delete(Long baseId) {
        repository.deleteByBaseId(baseId);
    }

}
