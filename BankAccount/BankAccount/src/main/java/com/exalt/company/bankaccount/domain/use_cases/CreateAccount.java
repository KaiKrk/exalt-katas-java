package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class CreateAccount {

    private final AccountPort accountPort;

    CreateAccount(AccountPort accountPort){
        this.accountPort = accountPort;
    }


    public Account execute(Account account){
       return accountPort.create(account);
    }
}
