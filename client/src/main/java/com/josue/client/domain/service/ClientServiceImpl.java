package com.josue.client.domain.service;

import com.josue.client.domain.entities.Client;
import com.josue.client.domain.exception.NotFoundEntityException;
import com.josue.client.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        var newClient = client.toBuilder()
                .id(UUID.randomUUID().toString())
                .build();
        newClient = clientRepository.create(newClient);
        return newClient;
    }

    @Override
    public Client edit(Client client) {
        client = clientRepository.edit(client);
        return client;
    }

    @Override
    public Client refresh(String id) {
        return clientRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("client", Map.of("id", id)));
    }

    @Override
    public boolean delete(String id) {
        var result = clientRepository.delete(id);
        return result;
    }

    @Override
    public Client getById(String id) {
        return clientRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("client", Map.of("id", id)));
    }

    @Override
    public List<Client> getAll() {
        var result = clientRepository.getAll()
                .stream();
        return result
                .toList();
    }

    @Override
    public List<Client> getAllByIds(List<String> ids) {
        var result = clientRepository.getAllByIds(ids)
                .stream();
        return result
                .toList();
    }
}
