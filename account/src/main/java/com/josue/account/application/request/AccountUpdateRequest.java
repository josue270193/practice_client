package com.josue.account.application.request;

import com.josue.account.domain.entities.AccountStatus;
import com.josue.account.domain.entities.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountUpdateRequest {

    @NotEmpty
    private String number;
    @NotNull
    private AccountType type;
    @NotNull
    private BigDecimal initialBalance;
    @NotNull
    private AccountStatus status;

}
