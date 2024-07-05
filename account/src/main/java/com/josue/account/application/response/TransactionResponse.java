package com.josue.account.application.response;

import com.josue.account.domain.entities.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@ToString
@Builder
public class TransactionResponse {

    private String id;
    private String accountId;
    private OffsetDateTime date;
    private TransactionType type;
    private BigDecimal amount;
    private BigDecimal balance;

}
