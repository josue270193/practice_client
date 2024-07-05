package com.josue.client.application.response;

import com.josue.client.domain.entities.ClientStatus;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder
public class ClientResponse extends PersonResponse{

    private String id;
    private String password;
    private ClientStatus status;

}
