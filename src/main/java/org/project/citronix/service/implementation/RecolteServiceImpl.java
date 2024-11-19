package org.project.citronix.service.implementation;

import org.project.citronix.entity.Recolte;
import org.project.citronix.repository.RecolteRepository;
import org.project.citronix.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecolteServiceImpl extends GenericServiceImpl<Recolte, Long> {
    private final RecolteRepository repository;

    public RecolteServiceImpl(RecolteRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
