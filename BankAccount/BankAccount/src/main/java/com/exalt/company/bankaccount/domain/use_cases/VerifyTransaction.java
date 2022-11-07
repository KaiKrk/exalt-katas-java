package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;

public class VerifyTransaction {

    /**
     * Check if the transaction amount will set the available funds value to less than 0
     * @param account
     * @param transaction
     * @return
     */
    public static Boolean verifyFunds(Account account, Transaction transaction){
        if (account.getFunds() - transaction.getAmount() >= 0 && verifyAmount(transaction)){
            return true;
        } else return false;
    }

    public static Boolean verifyAmount(Transaction transaction){
        if (transaction.getAmount() > 0){
            return true;
        } else return false;
    }
}
