package org.project.citronix.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.citronix.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {
    protected final JpaRepository<T, ID> repository;

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID aLong) {
        return repository.findById(aLong);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(ID aLong) {
        repository.deleteById(aLong);
    }
}
