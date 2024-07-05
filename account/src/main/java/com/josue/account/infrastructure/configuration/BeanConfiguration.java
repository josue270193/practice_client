package com.josue.account.infrastructure.configuration;

import com.josue.account.domain.repository.AccountRepository;
import com.josue.account.domain.repository.ClientRepository;
import com.josue.account.domain.repository.TransactionRepository;
import com.josue.account.domain.service.AccountService;
import com.josue.account.domain.service.AccountServiceImpl;
import com.josue.account.domain.service.TransactionService;
import com.josue.account.domain.service.TransactionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountService accountService(
            AccountRepository accountRepository,
            ClientRepository clientRepository
    ) {
        return new AccountServiceImpl(accountRepository, clientRepository);
    }

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository,
                                                 AccountRepository accountRepository
    ) {
        return new TransactionServiceImpl(transactionRepository, accountRepository);
    }

}
