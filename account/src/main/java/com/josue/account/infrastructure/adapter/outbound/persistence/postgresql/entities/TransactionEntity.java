package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities;

import com.josue.account.domain.entities.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "TRANSACTIONS", schema = "practice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @NotEmpty
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @NotNull
    private OffsetDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal balance;

}
