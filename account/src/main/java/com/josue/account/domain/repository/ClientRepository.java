package com.josue.account.domain.repository;

import com.josue.account.domain.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> getById(String id);

    List<Client> getByIds(List<String> id);

}
