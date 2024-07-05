package com.josue.account.domain.service;

import com.josue.account.domain.entities.Account;
import com.josue.account.domain.entities.Client;
import com.josue.account.domain.exception.NotFoundEntityException;
import com.josue.account.domain.repository.AccountRepository;
import com.josue.account.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Override
    public Account create(Account account) {
        var existClient = clientRepository.getById(account.getClientId())
                .orElseThrow(() -> new NotFoundEntityException("client"));
        var newAccount = account.toBuilder()
                .id(UUID.randomUUID().toString())
                .clientId(existClient.getId())
                .clientName(existClient.getName())
                .build();
        newAccount = accountRepository.create(newAccount);
        return newAccount;
    }

    @Override
    public Account edit(Account account) {
        account = accountRepository.edit(account);
        return account;
    }

    @Override
    public Account refresh(String id) {
        return accountRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("account", Map.of("id", id)));
    }

    @Override
    public boolean delete(String id) {
        accountRepository.delete(id);
        return true;
    }

    @Override
    public Account getById(String id) {
        return accountRepository.getById(id)
                .map(account -> {
                    var name = clientRepository.getById(account.getClientId())
                            .map(Client::getName)
                            .orElse("");
                    account.setClientName(name);
                    return account;
                })
                .orElseThrow(() -> new NotFoundEntityException("account", Map.of("id", id)));
    }

    @Override
    public List<Account> getAll() {
        var result = accountRepository.getAll();

        var clientIdList = result.stream()
                .map(Account::getClientId)
                .filter(StringUtils::hasText)
                .distinct()
                .toList();

        var nameList = clientRepository.getByIds(clientIdList)
                .stream()
                .collect(Collectors.toMap(Client::getId, Function.identity()));

        return result.stream()
                .map(account -> {
                    var client = nameList.get(account.getClientId());
                    var name = "";
                    if (client != null) {
                        name = client.getName();
                    }
                    account.setClientName(name);
                    return account;
                })
                .toList();
    }

    @Override
    public List<Account> reportByParam(OffsetDateTime dateFrom, OffsetDateTime dateTo, List<String> clientIds) {
        return accountRepository.reportByParam(dateFrom, dateTo, clientIds);
    }
}
