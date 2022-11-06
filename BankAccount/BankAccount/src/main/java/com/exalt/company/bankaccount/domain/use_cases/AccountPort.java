package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;

public interface AccountPort {

    Account updateAccount(Account account);

    Account getAccount(String accountId);

    Account create(Account account);

}
