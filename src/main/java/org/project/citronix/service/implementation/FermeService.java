package org.project.citronix.service.implementation;

import org.project.citronix.entity.Ferme;
import org.project.citronix.repository.FermeRepository;
import org.springframework.stereotype.Service;

@Service
public class FermeService extends GenericServiceImpl<Ferme, Long> {
    private final FermeRepository repository;

    public FermeService(FermeRepository repository) {
        super(repository);
        this.repository = repository;
    }

}