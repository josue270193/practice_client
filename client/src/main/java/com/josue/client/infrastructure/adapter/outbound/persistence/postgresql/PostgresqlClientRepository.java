package com.josue.client.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.client.domain.entities.Client;
import com.josue.client.domain.exception.NotFoundEntityException;
import com.josue.client.domain.repository.ClientRepository;
import com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.mapper.ClientEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostgresqlClientRepository implements ClientRepository {

    private final SpringPostgresqlClientRepository repository;

    @Override
    public Client create(Client client) {
        var entity = ClientEntityMapper.toEntity(client);
        entity = repository.save(entity);
        return ClientEntityMapper.toDomain(entity);
    }

    @Override
    public Client edit(Client client) {
        var oldEntity = repository.findById(client.getId())
                .orElseThrow(() -> new NotFoundEntityException("client", Map.of("id", client.getId())));

        var entity = ClientEntityMapper.toEntity(client);
        entity.setDocumentNumber(oldEntity.getDocumentNumber());
        entity = repository.save(entity);
        return ClientEntityMapper.toDomain(entity);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Client> getById(String id) {
        return repository.findById(id)
                .map(ClientEntityMapper::toDomain);
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll()
                .stream()
                .map(ClientEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Client> getAllByIds(List<String> ids) {
        return repository.findAllByIdIn(ids)
                .stream()
                .map(ClientEntityMapper::toDomain)
                .toList();
    }
}
