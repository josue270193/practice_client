package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper;

import com.josue.account.domain.entities.Account;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.AccountEntity;

import java.util.stream.Collectors;

public class AccountEntityMapper {

    private AccountEntityMapper() {
    }

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .clientId(account.getClientId())
                .number(account.getNumber())
                .type(account.getType())
                .status(account.getStatus())
                .initialBalance(account.getInitialBalance())
                .transactions(
                        account.getTransactions()
                                .stream()
                                .map(TransactionEntityMapper::toEntity)
                                .collect(Collectors.toList()))
                .build();
    }

    public static Account toDomain(AccountEntity entity) {
        var actualBalance = entity.getInitialBalance();
        if (entity.getLastTransaction() != null) {
            actualBalance = entity.getLastTransaction().getBalance();
        }
        return Account.builder()
                .id(entity.getId())
                .clientId(entity.getClientId())
                .number(entity.getNumber())
                .type(entity.getType())
                .initialBalance(entity.getInitialBalance())
                .actualBalance(actualBalance)
                .status(entity.getStatus())
                .transactions(entity.getTransactions()
                                      .stream()
                                      .map(TransactionEntityMapper::toDomain)
                                      .collect(Collectors.toList()))
                .build();
    }

    public static AccountEntity toEntityUpdate(AccountEntity oldEntity, Account account) {
        return oldEntity.toBuilder()
                .number(account.getNumber())
                .type(account.getType())
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .build();
    }
}
