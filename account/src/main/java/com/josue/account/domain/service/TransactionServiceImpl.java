package com.josue.account.domain.service;

import com.josue.account.domain.entities.Transaction;
import com.josue.account.domain.entities.TransactionType;
import com.josue.account.domain.exception.BalanceInsufficientException;
import com.josue.account.domain.exception.NotFoundEntityException;
import com.josue.account.domain.repository.AccountRepository;
import com.josue.account.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public Transaction create(Transaction transaction) {
        var existAccount = accountRepository.getById(transaction.getAccountId())
                .orElseThrow(() -> new NotFoundEntityException("account"));
        var type = TransactionType.WITHDRAWAL;
        if (transaction.getAmount() != null && transaction.getAmount().compareTo(BigDecimal.ZERO) >= 0) {
            type = TransactionType.DEPOSIT;
        }
        var balance = existAccount.getActualBalance().add(transaction.getAmount());

        if (type.compareTo(TransactionType.WITHDRAWAL) == 0 &&
            balance.compareTo(BigDecimal.ZERO) < 0
        ) {
            throw new BalanceInsufficientException();
        }

        var newTransaction = transaction.toBuilder()
                .id(UUID.randomUUID().toString())
                .date(OffsetDateTime.now())
                .type(type)
                .balance(balance)
                .build();
        newTransaction = transactionRepository.create(newTransaction);
        accountRepository.addTransaction(newTransaction);
        return newTransaction;
    }

    @Override
    public Transaction edit(Transaction transaction) {
        transaction = transactionRepository.edit(transaction);
        return transaction;
    }

    @Override
    public Transaction refresh(String id) {
        return this.getById(id);
    }

    @Override
    public boolean delete(String id) {
        var transaction = this.getById(id);
        transactionRepository.delete(id);
        accountRepository.deleteTransaction(transaction);
        return true;
    }

    @Override
    public Transaction getById(String id) {
        return transactionRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("transaction", Map.of("id", id)));
    }

    @Override
    public List<Transaction> getAll() {
        var result = transactionRepository.getAll().stream();
        return result.toList();
    }
}
