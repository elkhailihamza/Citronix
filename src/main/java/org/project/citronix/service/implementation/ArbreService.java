package org.project.citronix.service.implementation;

import org.project.citronix.entity.Arbre;
import org.project.citronix.repository.ArbreRepository;
import org.springframework.stereotype.Service;

@Service
public class ArbreService extends GenericServiceImpl<Arbre, Long> {
    private final ArbreRepository repository;

    public ArbreService(ArbreRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
