package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTransactionHistory  {

    private final TransactionPort transactionPort;

    public RetrieveTransactionHistory(TransactionPort transactionPort){
        this.transactionPort = transactionPort;
    }
    public List<Transaction> execute(String accountId) {
        return transactionPort.getHistory(accountId);
    }
}
