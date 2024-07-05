package com.josue.client.infrastructure.configuration;

import com.josue.client.domain.repository.ClientRepository;
import com.josue.client.domain.service.ClientService;
import com.josue.client.domain.service.ClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

}
