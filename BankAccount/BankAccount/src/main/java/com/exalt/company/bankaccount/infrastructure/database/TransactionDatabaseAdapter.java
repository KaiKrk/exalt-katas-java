package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.use_cases.TransactionPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDatabaseAdapter implements TransactionPort {
    @Override
    public List<Transaction> getHistory(String id) {
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }
}
