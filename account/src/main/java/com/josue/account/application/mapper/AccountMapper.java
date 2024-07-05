package com.josue.account.application.mapper;

import com.josue.account.application.request.AccountCreateRequest;
import com.josue.account.application.request.AccountUpdateRequest;
import com.josue.account.application.response.AccountResponse;
import com.josue.account.domain.entities.Account;

import java.util.ArrayList;

public class AccountMapper {

    private AccountMapper() {
    }

    public static AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .clientId(account.getClientId())
                .clientName(account.getClientName())
                .type(account.getType())
                .number(account.getNumber())
                .initialBalance(account.getInitialBalance())
                .actualBalance(account.getActualBalance())
                .status(account.getStatus())
                .build();
    }

    public static Account toDomain(AccountCreateRequest request) {
        return Account.builder()
                .clientId(request.getClientId())
                .number(request.getNumber())
                .type(request.getType())
                .initialBalance(request.getInitialBalance())
                .status(request.getStatus())
                .transactions(new ArrayList<>())
                .build();
    }

    public static Account toDomain(AccountUpdateRequest request) {
        return Account.builder()
                .number(request.getNumber())
                .type(request.getType())
                .initialBalance(request.getInitialBalance())
                .status(request.getStatus())
                .build();
    }
}
