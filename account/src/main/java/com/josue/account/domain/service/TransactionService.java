package com.josue.account.domain.service;

import com.josue.account.domain.entities.Account;
import com.josue.account.domain.entities.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction create(Transaction transaction);

    Transaction edit(Transaction transaction);

    Transaction refresh(String id);

    boolean delete(String id);

    Transaction getById(String id);

    List<Transaction> getAll();

}
