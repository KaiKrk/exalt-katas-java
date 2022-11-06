package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieveAccount {

    private final AccountPort accountPort;

    RetrieveAccount(AccountPort accountPort){
        this.accountPort = accountPort;
    }

    public Account execute(String accountId){
      return accountPort.getAccount(accountId);
    };
}
