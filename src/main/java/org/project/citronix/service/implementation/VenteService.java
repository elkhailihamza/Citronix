package org.project.citronix.service.implementation;

import org.project.citronix.entity.Vente;
import org.project.citronix.repository.VenteRepository;
import org.springframework.stereotype.Service;

@Service
public class VenteService extends GenericServiceImpl<Vente, Long> {
    private final VenteRepository repository;

    public VenteService(VenteRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
