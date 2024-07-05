package com.josue.client.application.request;

import lombok.Data;

import java.util.List;

@Data
public class ClientSearchRequest {

    private List<String> ids;

}
