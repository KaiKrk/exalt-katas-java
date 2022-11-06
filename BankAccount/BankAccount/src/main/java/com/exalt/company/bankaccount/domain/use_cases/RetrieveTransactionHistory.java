package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveTransactionHistory  {

    private final TransactionPort transactionPort;

    private final RetrieveAccount retrieveAccount;

    public RetrieveTransactionHistory(TransactionPort transactionPort, RetrieveAccount retrieveAccount){
        this.transactionPort = transactionPort;
        this.retrieveAccount = retrieveAccount;
    }
    public List<Transaction> execute(String accountId) {

        //retrieve transaction will check if account id is valid if not then throw NoAccountFoundException
        retrieveAccount.execute(accountId);

        return transactionPort.getHistory(accountId);
    }
}
