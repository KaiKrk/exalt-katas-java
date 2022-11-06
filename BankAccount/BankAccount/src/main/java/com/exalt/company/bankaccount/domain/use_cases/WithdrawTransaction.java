package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTransaction {

    private final RetrieveAccount retrieveAccount;
    private final AccountPort accountPort;

    private final TransactionPort transactionPort;

    WithdrawTransaction(AccountPort accountPort,TransactionPort transactionPort,RetrieveAccount retrieveAccount){
        this.retrieveAccount = retrieveAccount;
        this.transactionPort = transactionPort;
        this.accountPort = accountPort;
    }


    public Account executeWithdrawTransaction(Account account, Transaction transaction) {

//        Account account = retrieveAccount.execute(accountId);

//        Account userAccount = retrieveAccount.execute(accountId);


        if(VerifyTransaction.verifyFunds(account,transaction)){
            transaction.setSuccesful(true);
            transactionPort.save(transaction);

            account.setFunds(account.getFunds()+transaction.getAmount());

            return  accountPort.updateAccount(account);

        } else {
            transaction.setSuccesful(false);
            transactionPort.save(transaction);
          throw new NotEnoughFundsException();
        }
    }
}
