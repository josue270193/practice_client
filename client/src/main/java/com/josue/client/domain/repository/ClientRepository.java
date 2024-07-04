package com.josue.client.domain.repository;

import com.josue.client.domain.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client create(Client client);

    Client edit(Client client);

    boolean delete(String id);

    Optional<Client> getById(String id);

    List<Client> getAll();

}
