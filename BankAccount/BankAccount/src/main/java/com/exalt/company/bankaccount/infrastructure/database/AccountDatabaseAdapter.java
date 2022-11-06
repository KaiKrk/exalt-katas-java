package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.use_cases.AccountPort;
import com.exalt.company.bankaccount.domain.use_cases.NoAccountFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class AccountDatabaseAdapter implements AccountPort {

    private final AccountJpaRepository accountJpaRepository;

    public AccountDatabaseAdapter(AccountJpaRepository accountJpaRepository){
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account create(Account account) {
        return AccountJpa.toAccount(accountJpaRepository.save(new AccountJpa(account)));
    }

    @Override
    public Account getAccount(String accountId) {
        Optional<AccountJpa> account =  accountJpaRepository.findById(Integer.parseInt(accountId));

        if(account.isPresent()){
            return AccountJpa.toAccount(account.get());
        } else {
            throw new NoAccountFoundException();
        }

    }

    @Override
    public Account updateAccount(Account account) {
        return AccountJpa.toAccount(accountJpaRepository.save(new AccountJpa(account)));
    }


}
