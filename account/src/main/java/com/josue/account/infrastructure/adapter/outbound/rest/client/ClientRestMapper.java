package com.josue.account.infrastructure.adapter.outbound.rest.client;

import com.josue.account.domain.entities.Client;
import com.josue.account.infrastructure.adapter.outbound.rest.client.response.ClientRestResponse;

public class ClientRestMapper {
    public static Client toDomain(ClientRestResponse response) {
        return Client.builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }
}
