package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Transaction;

import java.util.List;

public interface TransactionHistoryPort {


    List<Transaction> getHistory(String id);

    Transaction save(Transaction transaction);
}
