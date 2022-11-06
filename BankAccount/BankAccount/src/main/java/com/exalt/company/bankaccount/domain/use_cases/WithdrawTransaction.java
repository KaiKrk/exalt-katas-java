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


    public Account executeWithdrawTransaction(Account account, Transaction transaction) throws InterruptedException {

        if(VerifyTransaction.verifyFunds(account,transaction)){
            account.getSemaphore().acquire();
            transaction.setSuccesful(true);
            transactionPort.save(transaction);

            account.setFunds(account.getFunds()+transaction.getAmount());
            account.getSemaphore().release();
            return  accountPort.updateAccount(account);

        } else {
            account.getSemaphore().acquire();

            transaction.setSuccesful(false);
            transactionPort.save(transaction);

            account.getSemaphore().release();
          throw new NotEnoughFundsException();
        }
    }
}
