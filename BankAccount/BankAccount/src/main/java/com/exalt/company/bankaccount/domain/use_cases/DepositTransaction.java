package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.application.rest.AccountApi;
import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

    public AccountApi executeDepositTransaction(Account userAccount,Transaction transaction){

        if(VerifyTransaction.verifyAmount(transaction)){
            transaction.setType(TransactionType.DEPOSIT);
            transaction.setAccount(userAccount.getId());
            transaction.setDate(LocalDate.now());
            transaction.setSuccesful(true);
            transactionPort.save(transaction);

            userAccount.setFunds(userAccount.getFunds()+transaction.getAmount());

            return AccountApi.toAccountApi( accountPort.updateAccount(userAccount));
        } else {

            throw new IllegalArgumentException();
        }


    };
}
