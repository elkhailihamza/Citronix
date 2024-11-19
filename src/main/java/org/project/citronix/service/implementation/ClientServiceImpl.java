package org.project.citronix.service.implementation;

import org.project.citronix.entity.Client;
import org.project.citronix.repository.ClientRepository;
import org.project.citronix.repository.FermeRepository;
import org.project.citronix.service.GenericService;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl extends GenericServiceImpl<Client, Long>{
    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
