package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper;

import com.josue.account.domain.entities.Transaction;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.AccountEntity;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.TransactionEntity;

public class TransactionEntityMapper {

    private TransactionEntityMapper() {
    }

    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .account(AccountEntity.builder()
                                 .id(transaction.getAccountId())
                                 .build())
                .date(transaction.getDate())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .build();
    }

    public static Transaction toDomain(TransactionEntity entity) {
        return Transaction.builder()
                .id(entity.getId())
                .accountId(entity.getAccount().getId())
                .date(entity.getDate())
                .type(entity.getType())
                .amount(entity.getAmount())
                .balance(entity.getBalance())
                .build();
    }
}
