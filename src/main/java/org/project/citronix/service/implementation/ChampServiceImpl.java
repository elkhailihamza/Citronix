package org.project.citronix.service.implementation;

import org.project.citronix.entity.Champ;
import org.project.citronix.repository.ChampRepository;
import org.project.citronix.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChampServiceImpl extends GenericServiceImpl<Champ, Long> {
    private final ChampRepository repository;

    public ChampServiceImpl(ChampRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
