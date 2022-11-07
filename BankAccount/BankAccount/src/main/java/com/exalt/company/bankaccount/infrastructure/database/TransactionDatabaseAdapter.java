package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.use_cases.TransactionPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionDatabaseAdapter implements TransactionPort {

    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    TransactionDatabaseAdapter(TransactionJpaRepository transactionJpaRepository,  AccountJpaRepository accountJpaRepository){
        this.transactionJpaRepository = transactionJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }
    @Override
    public List<Transaction> getHistory(String id) {
        return transactionJpaRepository.findAllByAccount_Id(id).stream().map(this::toTransaction).collect(Collectors.toList());
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionJpa transactionJpa = new TransactionJpa(transaction);
        transactionJpa.setAccount(accountJpaRepository.findById(transaction.getAccount()).get());
        return toTransaction(transactionJpaRepository.save(transactionJpa));
    }

    private Transaction toTransaction(TransactionJpa transactionJpa){
        Transaction transaction = new Transaction();
        transaction.setId(transactionJpa.getId());
        transaction.setAccount(transactionJpa.getAccount().getId());
        transaction.setDate(transactionJpa.getDate());
        transaction.setAmount(transactionJpa.getAmount());
        transaction.setSuccesful(transactionJpa.getIsSuccesful());
        return transaction;
    }
}
