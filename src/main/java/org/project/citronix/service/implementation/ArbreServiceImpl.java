package org.project.citronix.service.implementation;

import org.project.citronix.entity.Arbre;
import org.project.citronix.repository.ArbreRepository;

public class ArbreServiceImpl extends GenericServiceImpl<Arbre, Long> {
    private final ArbreRepository repository;

    public ArbreServiceImpl(ArbreRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
