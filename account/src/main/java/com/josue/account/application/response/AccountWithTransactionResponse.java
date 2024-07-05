package com.josue.account.application.response;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@ToString
@SuperBuilder
public class AccountWithTransactionResponse extends AccountResponse {

    private List<TransactionResponse> transactions;

}
