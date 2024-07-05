package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.account.domain.entities.Transaction;
import com.josue.account.domain.repository.TransactionRepository;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper.TransactionEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostgresqlTransactionRepository implements TransactionRepository {

    private final SpringPostgresqlTransactionRepository repository;

    @Override
    public Transaction create(Transaction transaction) {
        var entity = TransactionEntityMapper.toEntity(transaction);
        entity = repository.save(entity);
        return TransactionEntityMapper.toDomain(entity);
    }

    @Override
    public Transaction edit(Transaction transaction) {
        var entity = TransactionEntityMapper.toEntity(transaction);
        entity = repository.save(entity);
        return TransactionEntityMapper.toDomain(entity);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Transaction> getById(String id) {
        return repository.findById(id)
                .map(TransactionEntityMapper::toDomain);
    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll()
                .stream()
                .map(TransactionEntityMapper::toDomain)
                .toList();
    }
}
