package com.josue.account.infrastructure.adapter.outbound.rest.client;

import com.josue.account.domain.entities.Client;
import com.josue.account.domain.repository.ClientRepository;
import com.josue.account.infrastructure.adapter.outbound.rest.client.request.ClientSearchRequest;
import com.josue.account.infrastructure.adapter.outbound.rest.client.response.ClientRestResponse;
import com.josue.account.infrastructure.configuration.ApplicationConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RestClientRepository implements ClientRepository {

    private final ApplicationConfiguration appConfig;

    @Override
    public Optional<Client> getById(String id) {
        return WebClient.create()
                .get()
                .uri(appConfig.getClientUrl() + "/" + id)
                .retrieve()
                .bodyToMono(ClientRestResponse.class)
                .map(ClientRestMapper::toDomain)
                .map(Optional::of)
                .onErrorResume(WebClientResponseException.class,
                               ex -> ex.getStatusCode().value() == 404 ? Mono.empty() : Mono.error(ex)
                )
                .defaultIfEmpty(Optional.empty())
                .block();
    }

    @Override
    public List<Client> getByIds(List<String> id) {
        var request = new ClientSearchRequest(id);
        return WebClient.create()
                .post()
                .uri(appConfig.getClientUrl() + "/search")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(ClientRestResponse.class)
                .map(ClientRestMapper::toDomain)
                .onErrorResume(WebClientResponseException.class,
                               ex -> ex.getStatusCode().value() == 404 ? Mono.empty() : Mono.error(ex)
                )
                .collectList()
                .defaultIfEmpty(List.of())
                .block();
    }
}
