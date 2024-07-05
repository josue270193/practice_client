package com.josue.account.application.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionCreateRequest {

    @NotEmpty
    private String accountId;
    @NotNull
    private BigDecimal amount;

}
