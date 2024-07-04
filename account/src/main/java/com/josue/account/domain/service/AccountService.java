package com.josue.account.domain.service;

import com.josue.account.domain.entities.Account;

import java.util.List;

public interface AccountService {

    Account create(Account account);

    Account edit(Account account);

    Account refresh(String id);

    boolean delete(String id);

    Account getById(String id);

    List<Account> getAll();

}
