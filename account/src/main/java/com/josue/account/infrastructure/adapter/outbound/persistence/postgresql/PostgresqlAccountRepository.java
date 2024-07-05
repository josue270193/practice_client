package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.account.domain.entities.Account;
import com.josue.account.domain.entities.Transaction;
import com.josue.account.domain.exception.NotFoundEntityException;
import com.josue.account.domain.repository.AccountRepository;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper.AccountEntityMapper;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper.TransactionEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostgresqlAccountRepository implements AccountRepository {

    private final SpringPostgresqlAccountRepository repository;

    @Override
    public Account create(Account account) {
        var entity = AccountEntityMapper.toEntity(account);
        entity.setLastTransaction(null);
        entity = repository.save(entity);
        return AccountEntityMapper.toDomain(entity);
    }

    @Override
    public Account edit(Account account) {
        var oldEntity = repository.findByIdWithTransactions(account.getId())
                .orElseThrow(() -> new NotFoundEntityException("account", Map.of("id", account.getId())));

        var entity = AccountEntityMapper.toEntityUpdate(oldEntity, account);
        entity = repository.save(entity);
        entity.setLastTransaction(oldEntity.getLastTransaction());
        entity.setTransactions(oldEntity.getTransactions());
        return AccountEntityMapper.toDomain(entity);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Account> getById(String id) {
        return repository.findByIdWithTransactions(id)
                .map(AccountEntityMapper::toDomain);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAllWithTransactions()
                .stream()
                .map(AccountEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean addTransaction(Transaction transaction) {
        var account = repository.findById(transaction.getAccountId())
                .orElseThrow(() -> new NotFoundEntityException("account", Map.of("id", transaction.getAccountId())));
        var transactionEntity = TransactionEntityMapper.toEntity(transaction);

        account.setLastTransaction(transactionEntity);

        repository.save(account);
        return true;
    }

    @Override
    public boolean deleteTransaction(Transaction transaction) {
        var account = repository.findById(transaction.getAccountId())
                .orElseThrow(() -> new NotFoundEntityException("account", Map.of("id", transaction.getAccountId())));

        account.setLastTransaction(null);

        repository.save(account);
        return true;
    }
}
