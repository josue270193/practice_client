package com.josue.client.domain.service;

import com.josue.client.domain.entities.Client;

import java.util.List;

public interface ClientService {

    Client create(Client client);

    Client edit(Client client);

    Client refresh(String id);

    boolean delete(String id);

    Client getById(String id);

    List<Client > getAll();

}
