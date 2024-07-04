package com.josue.account.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private String id;
    private OffsetDateTime date;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balance;

}
