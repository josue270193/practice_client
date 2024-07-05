package com.josue.account.infrastructure.adapter.outbound.rest.client.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRestResponse {

    private String id;
    private String status;
    private String documentNumber;
    private String name;
    private String gender;
    private OffsetDateTime bornDate;
    private String address;
    private String phoneNumber;

}
