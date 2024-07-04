package com.josue.account.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private String id;
    private String number;
    private AccountType type;
    private BigDecimal initialBalance;
    private BigDecimal actualBalance;
    private AccountStatus status;

}
