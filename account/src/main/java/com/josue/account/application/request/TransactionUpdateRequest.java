package com.josue.account.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionUpdateRequest {

    @NotNull
    private BigDecimal amount;
}
