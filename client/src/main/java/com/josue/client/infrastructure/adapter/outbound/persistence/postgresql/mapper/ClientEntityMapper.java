package com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.mapper;

import com.josue.client.domain.entities.Client;
import com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.entities.ClientEntity;

public class ClientEntityMapper {

    private ClientEntityMapper() {

    }

    public static ClientEntity toEntity(Client client) {
        return ClientEntity.builder()
                .id(client.getId())
                .password(client.getPassword())
                .status(client.getStatus())
                .documentNumber(client.getDocumentNumber())
                .name(client.getName())
                .gender(client.getGender())
                .address(client.getAddress())
                .bornDate(client.getBornDate())
                .phoneNumber(client.getPhoneNumber())
                .build();
    }

    public static Client toDomain(ClientEntity entity) {
        return Client.builder()
                .id(entity.getId())
                .password(entity.getPassword())
                .status(entity.getStatus())
                .documentNumber(entity.getDocumentNumber())
                .name(entity.getName())
                .gender(entity.getGender())
                .address(entity.getAddress())
                .bornDate(entity.getBornDate())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

}
