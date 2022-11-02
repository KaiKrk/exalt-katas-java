package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTransactionHistory  {

    private final TransactionHistoryPort transactionHistoryPort;

    public RetrieveTransactionHistory(TransactionHistoryPort transactionHistoryPort){
        this.transactionHistoryPort = transactionHistoryPort;
    }
    public List<Transaction> execute(String accountId) {
        return transactionHistoryPort.getHistory(accountId);
    }
}
