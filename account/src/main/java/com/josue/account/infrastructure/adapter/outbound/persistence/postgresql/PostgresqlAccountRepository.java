package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.account.domain.entities.Account;
import com.josue.account.domain.entities.Transaction;
import com.josue.account.domain.exception.NotFoundEntityException;
import com.josue.account.domain.repository.AccountRepository;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.AccountEntity;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.AccountEntity_;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.TransactionEntity_;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper.AccountEntityMapper;
import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.mapper.TransactionEntityMapper;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    @Override
    public List<Account> reportByParam(OffsetDateTime dateFrom, OffsetDateTime dateTo, List<String> clientIds) {
        return repository.findAll(
                        Specification.where(filterByDateAndAccountIds(dateFrom, dateTo, clientIds))
                )
                .stream()
                .map(AccountEntityMapper::toDomain)
                .toList();
    }

    private Specification<AccountEntity> filterByDateAndAccountIds(OffsetDateTime dateFrom,
                                                                   OffsetDateTime dateTo,
                                                                   List<String> clientIds
    ) {
        return (root, query, cb) -> {
            root.fetch(AccountEntity_.TRANSACTIONS, JoinType.LEFT);
            root.fetch(AccountEntity_.LAST_TRANSACTION, JoinType.LEFT);

            var predicates = new ArrayList<Predicate>();
            if (!CollectionUtils.isEmpty(clientIds)) {
                predicates.add(
                        cb.in(root.get(AccountEntity_.CLIENT_ID)).value(clientIds)
                );
            }
            if (dateFrom != null && dateTo != null) {
                predicates.add(
                        cb.between(
                                root.get(AccountEntity_.TRANSACTIONS).get(TransactionEntity_.DATE),
                                dateFrom,
                                dateTo
                        )
                );
            } else if (dateFrom != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get(AccountEntity_.TRANSACTIONS).get(TransactionEntity_.DATE),
                                dateFrom
                        )
                );
            } else if (dateTo != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get(AccountEntity_.TRANSACTIONS).get(TransactionEntity_.DATE),
                                dateTo
                        )
                );
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
