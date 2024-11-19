package org.project.citronix.service.implementation;

import org.project.citronix.entity.Vente;
import org.project.citronix.repository.VenteRepository;
import org.project.citronix.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenteServiceImpl extends GenericServiceImpl<Vente, Long> {
    private final VenteRepository repository;

    public VenteServiceImpl(VenteRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
