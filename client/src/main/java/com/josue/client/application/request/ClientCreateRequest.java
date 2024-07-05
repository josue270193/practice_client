package com.josue.client.application.request;

import com.josue.client.domain.entities.ClientStatus;
import com.josue.client.domain.entities.PersonGender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ClientCreateRequest {

    @NotEmpty
    private String documentNumber;
    @NotEmpty
    private String name;
    @NotNull
    private PersonGender gender;
    @NotNull
    private OffsetDateTime bornDate;
    @NotEmpty
    private String address;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String password;
    @NotNull
    private ClientStatus status;
}
