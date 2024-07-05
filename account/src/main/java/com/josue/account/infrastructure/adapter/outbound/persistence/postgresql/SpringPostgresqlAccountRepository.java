package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.AccountEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

@Repository
public interface SpringPostgresqlAccountRepository extends JpaRepository<AccountEntity, String>, JpaSpecificationExecutor<AccountEntity> {

    @Query("FROM AccountEntity ae WHERE ae.id = :id")
    @EntityGraph(attributePaths = {"lastTransaction", "transactions"}, type = LOAD)
    Optional<AccountEntity> findByIdWithTransactions(String id);

    @Query("FROM AccountEntity ae")
    @EntityGraph(attributePaths = {"lastTransaction", "transactions"}, type = LOAD)
    List<AccountEntity> findAllWithTransactions();

}
