package com.josue.account.domain.repository;

import com.josue.account.domain.entities.Account;
import com.josue.account.domain.entities.Transaction;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account create(Account account);

    Account edit(Account account);

    boolean delete(String id);

    Optional<Account> getById(String id);

    List<Account> getAll();

    boolean addTransaction(Transaction transaction);

    boolean deleteTransaction(Transaction transaction);

    List<Account> reportByParam(OffsetDateTime dateFrom, OffsetDateTime dateTo, List<String> clientIds);
}
