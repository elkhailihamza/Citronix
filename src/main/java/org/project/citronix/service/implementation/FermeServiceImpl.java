package org.project.citronix.service.implementation;

import org.project.citronix.entity.Ferme;
import org.project.citronix.repository.FermeRepository;

import java.util.List;
import java.util.Optional;

public class FermeServiceImpl extends GenericServiceImpl<Ferme, Long> {
    private final FermeRepository repository;

    public FermeServiceImpl(FermeRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
