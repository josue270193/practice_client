package com.josue.account.infrastructure.adapter.outbound.persistence.postgresql;

import com.josue.account.infrastructure.adapter.outbound.persistence.postgresql.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPostgresqlTransactionRepository extends JpaRepository<TransactionEntity, String> {


}
