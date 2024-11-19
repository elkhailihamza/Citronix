package org.project.citronix.service.implementation;

import org.project.citronix.entity.Recolte;
import org.project.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Service;

@Service
public class RecolteService extends GenericServiceImpl<Recolte, Long> {
    private final RecolteRepository repository;

    public RecolteService(RecolteRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
