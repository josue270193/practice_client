package com.josue.account.infrastructure.adapter.outbound.rest.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientSearchRequest {

    private List<String> ids;

}
