package com.josue.account.application.mapper;

import com.josue.account.application.request.TransactionCreateRequest;
import com.josue.account.application.request.TransactionUpdateRequest;
import com.josue.account.application.response.TransactionResponse;
import com.josue.account.domain.entities.Transaction;

public class TransactionMapper {

    private TransactionMapper() {
    }


    public static TransactionResponse toResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .date(transaction.getDate())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .build();
    }

    public static Transaction toDomain(TransactionCreateRequest request) {
        return Transaction.builder()
                .accountId(request.getAccountId())
                .amount(request.getAmount())
                .build();
    }

    public static Transaction toDomain(TransactionUpdateRequest request) {
        return Transaction.builder()
                .amount(request.getAmount())
                .build();
    }

}
