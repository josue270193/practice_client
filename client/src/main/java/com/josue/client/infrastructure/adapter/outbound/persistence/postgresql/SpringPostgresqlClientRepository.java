package com.josue.client.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringPostgresqlClientRepository extends JpaRepository<ClientEntity, String> {

    boolean existsByDocumentNumber(String documentNumber);

    List<ClientEntity> findAllByIdIn(List<String> ids);

}
