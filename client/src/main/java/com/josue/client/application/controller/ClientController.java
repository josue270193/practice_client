package com.josue.client.application.controller;

import com.josue.client.application.mapper.ClientMapper;
import com.josue.client.application.request.ClientCreateRequest;
import com.josue.client.application.request.ClientEditRequest;
import com.josue.client.application.request.ClientSearchRequest;
import com.josue.client.application.response.ClientResponse;
import com.josue.client.domain.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public Flux<ClientResponse> getAll() {
        return Flux.fromStream(
                clientService.getAll()
                        .stream()
                        .map(ClientMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public Mono<ClientResponse> getById(
            @PathVariable String id
    ) {
        return Mono.justOrEmpty(clientService.getById(id))
                .map(ClientMapper::toResponse);
    }

    @PostMapping()
    public Mono<ClientResponse> create(
            @Valid @RequestBody ClientCreateRequest request
    ) {
        var domain = ClientMapper.toDomain(request);
        return Mono.just(clientService.create(domain))
                .map(ClientMapper::toResponse);
    }

    @PostMapping("/search")
    public Flux<ClientResponse> search(
            @Valid @RequestBody ClientSearchRequest request
    ) {
        return Flux.fromStream(
                clientService.getAllByIds(request.getIds())
                        .stream()
                        .map(ClientMapper::toResponse)
        );
    }

    @PutMapping("/{id}")
    public Mono<ClientResponse> edit(
            @PathVariable String id,
            @Valid @RequestBody ClientEditRequest request
    ) {
        var domain = ClientMapper.toDomain(request);
        domain.setId(id);
        return Mono.just(clientService.edit(domain))
                .map(ClientMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> delete(
            @PathVariable String id
    ) {
        return Mono.justOrEmpty(clientService.delete(id))
                .map(result -> ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .build()
                );
    }

}
