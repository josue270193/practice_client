package com.josue.account.application.response;

import com.josue.account.domain.entities.AccountStatus;
import com.josue.account.domain.entities.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@Builder
public class AccountResponse {

    private String id;
    private String clientId;
    private String clientName;
    private String number;
    private AccountType type;
    private BigDecimal initialBalance;
    private BigDecimal actualBalance;
    private AccountStatus status;

}
