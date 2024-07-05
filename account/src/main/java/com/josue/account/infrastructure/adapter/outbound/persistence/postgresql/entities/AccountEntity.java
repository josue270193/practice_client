package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities;

import com.josue.account.domain.entities.AccountStatus;
import com.josue.account.domain.entities.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ACCOUNTS", schema = "practice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountEntity {

    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    @Column(name = "client_id")
    private String clientId;

    @NotEmpty
    private String number;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotNull
    @Column(name = "initial_balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal initialBalance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_transaction")
    private TransactionEntity lastTransaction;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<TransactionEntity> transactions;

}
