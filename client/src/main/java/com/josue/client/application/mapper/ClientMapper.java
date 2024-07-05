package com.josue.client.application.mapper;

import com.josue.client.application.request.ClientCreateRequest;
import com.josue.client.application.request.ClientEditRequest;
import com.josue.client.application.response.ClientResponse;
import com.josue.client.domain.entities.Client;

public class ClientMapper {

    private ClientMapper() {
    }

    public static ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
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

    public static Client toDomain(ClientCreateRequest request) {
        return Client.builder()
                .password(request.getPassword())
                .status(request.getStatus())
                .documentNumber(request.getDocumentNumber())
                .name(request.getName())
                .gender(request.getGender())
                .bornDate(request.getBornDate())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public static Client toDomain(ClientEditRequest request) {
        return Client.builder()
                .password(request.getPassword())
                .status(request.getStatus())
                .name(request.getName())
                .gender(request.getGender())
                .bornDate(request.getBornDate())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
