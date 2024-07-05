package com.josue.account.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Account {

    private String id;
    private String clientId;
    private String clientName;
    private String number;
    private AccountType type;
    private BigDecimal initialBalance;
    private BigDecimal actualBalance;
    private AccountStatus status;
    private List<Transaction> transactions;

}
