package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class WithdrawTransaction {


    public Transaction executeWithdrawTransaction(Account account, Transaction transaction){

        if(VerifyTransaction.verifyFunds(account,transaction)){

        } else {

        };

        return null;
    };
}
