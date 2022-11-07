package com.exalt.company.bankaccount.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<TransactionJpa, String> {

    List<TransactionJpa> findAllByAccount_Id(String accountId);
}
