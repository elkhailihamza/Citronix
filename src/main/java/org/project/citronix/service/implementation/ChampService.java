package org.project.citronix.service.implementation;

import org.project.citronix.entity.Champ;
import org.project.citronix.repository.ChampRepository;
import org.springframework.stereotype.Service;

@Service
public class ChampService extends GenericServiceImpl<Champ, Long> {
    private final ChampRepository repository;

    public ChampService(ChampRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
