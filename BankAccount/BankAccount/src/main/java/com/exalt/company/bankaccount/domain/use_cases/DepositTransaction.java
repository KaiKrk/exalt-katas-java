package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class DepositTransaction {

    private final AccountPort accountPort;

    private final TransactionPort transactionPort;

    private final RetrieveAccount retrieveAccount;

    DepositTransaction(AccountPort accountPort, TransactionPort transactionPort, RetrieveAccount retrieveAccount){
        this.accountPort = accountPort;
        this.transactionPort = transactionPort;
        this.retrieveAccount = retrieveAccount;
    }

    public Account executeDepositTransaction(String accountId,Transaction transaction){
        Account userAccount = retrieveAccount.execute(accountId);

        transaction.setSuccesful(true);
        Transaction newTransaction = transactionPort.save(transaction);

        userAccount.setFunds(userAccount.getFunds()+transaction.getAmount());

        return accountPort.updateAccount(userAccount);
    };
}
