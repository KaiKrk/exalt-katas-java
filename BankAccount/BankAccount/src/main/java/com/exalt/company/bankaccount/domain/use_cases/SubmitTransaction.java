package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class SubmitTransaction  {

    private final TransactionPort transactionPort;

    public SubmitTransaction(TransactionPort transactionPort){
        this.transactionPort = transactionPort;
    }



    public Account execute(Account account, Transaction transaction){

        if (VerifyTransaction.verifyFunds(account,transaction) == false)
            throw new IllegalArgumentException("Bank Transaction failed : not enough funds ");

        return transactionPort.updateAccount(account,transaction);
    }
}
