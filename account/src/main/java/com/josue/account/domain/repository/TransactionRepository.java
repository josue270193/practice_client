package com.josue.account.domain.repository;

import com.josue.account.domain.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Transaction create(Transaction transaction);

    Transaction edit(Transaction transaction);

    boolean delete(String id);

    Optional<Transaction> getById(String id);

    List<Transaction> getAll();

}
