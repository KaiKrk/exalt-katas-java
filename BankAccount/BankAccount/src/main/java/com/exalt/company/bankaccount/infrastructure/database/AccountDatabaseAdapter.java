package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.use_cases.AccountPort;
import org.springframework.stereotype.Component;

@Component
public class AccountDatabaseAdapter implements AccountPort {
    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public Account getAccount(String accountId) {
        return null;
    }

    @Override
    public Account create(Account account) {
        return null;
    }
}
