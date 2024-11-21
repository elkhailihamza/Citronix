package org.project.citronix.service.implementation;

import org.project.citronix.entity.Client;
import org.project.citronix.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericServiceImpl<Client, Long>{
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
