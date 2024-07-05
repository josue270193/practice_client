package com.josue.client.domain.service;

import com.josue.client.domain.entities.Client;
import com.josue.client.domain.exception.NotFoundEntityException;
import com.josue.client.domain.repository.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl service;

    @Test
    void testCreateOkThenOk() {
        var clientRequest = Client.builder().build();
        var clientResponse = Client.builder()
                .id(UUID.randomUUID().toString())
                .build();

        when(clientRepository.create(any(Client.class)))
                .thenReturn(clientResponse);

        var response = service.create(clientRequest);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId())
        );
    }

    @Test
    void testEditOkThenOk() {
        var clientRequest = Client.builder().build();
        var clientResponse = Client.builder()
                .id(UUID.randomUUID().toString())
                .build();

        when(clientRepository.edit(any(Client.class)))
                .thenReturn(clientResponse);

        var response = service.edit(clientRequest);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId())
        );
    }

    @Test
    void testRefreshOkThenOk() {
        var clientId = UUID.randomUUID().toString();
        var clientResponse = Client.builder()
                .id(clientId)
                .build();

        when(clientRepository.getById(any(String.class)))
                .thenReturn(Optional.ofNullable(clientResponse));

        var response = service.refresh(clientId);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId()),
                () -> assertEquals(clientId, response.getId())
        );
    }

    @Test
    void testDeleteOkThenOk() {
        var clientId = UUID.randomUUID().toString();

        when(clientRepository.delete(any(String.class)))
                .thenReturn(Boolean.TRUE);

        var response = service.delete(clientId);

        assertAll(
                () -> assertTrue(response)
        );
    }

    @Test
    void testGetByIdOkThenOk() {
        var clientId = UUID.randomUUID().toString();
        var clientResponse = Client.builder()
                .id(clientId)
                .build();

        when(clientRepository.getById(any(String.class)))
                .thenReturn(Optional.ofNullable(clientResponse));

        var response = service.getById(clientId);

        assertAll(
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId()),
                () -> assertEquals(clientId, response.getId())
        );
    }

    @Test
    void testGetByIdErrorThenError() {
        var clientId = UUID.randomUUID().toString();

        var exception = assertThrows(NotFoundEntityException.class, () -> {
            service.getById(clientId);
        });

        assertAll(
                () -> assertEquals("client", exception.getEntity())
        );
    }

    @Test
    void testGetAllOkThenOk() {
        var id = UUID.randomUUID().toString();
        var clientList = List.of(
                Client.builder()
                        .id(id)
                        .build(),
                Client.builder()
                        .id(UUID.randomUUID().toString())
                        .build()
        );

        when(clientRepository.getAll())
                .thenReturn(clientList);

        var response = service.getAll();

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(2, response.size()),
                () -> Assertions.assertThat(response)
                        .hasSize(2)
                        .extracting(Client::getId)
                        .contains(id)
        );
    }

    @Test
    void tesGetAllByIdsOkThenOk() {
        var id1 = UUID.randomUUID().toString();
        var id2 = UUID.randomUUID().toString();

        var clientList = List.of(
                Client.builder()
                        .id(id1)
                        .build(),
                Client.builder()
                        .id(id2)
                        .build()
        );

        when(clientRepository.getAllByIds(anyList()))
                .thenReturn(clientList);

        var response = service.getAllByIds(List.of(id1, id2));

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(2, response.size()),
                () -> Assertions.assertThat(response)
                        .hasSize(2)
                        .extracting(Client::getId)
                        .containsExactlyInAnyOrder(id1, id2)
        );
    }
}