package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.use_cases.AccountPort;
import com.exalt.company.bankaccount.domain.use_cases.NoAccountFoundException;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountDatabaseAdapter implements AccountPort {

    private final AccountJpaRepository accountJpaRepository;

    public AccountDatabaseAdapter(AccountJpaRepository accountJpaRepository){
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account create(Account account) {
        AccountJpa accountJpa = new AccountJpa(account);
        accountJpa.setId(UUID.randomUUID().toString());
        return toAccount(accountJpaRepository.save(accountJpa));
    }

    @Override
    public Account getAccount(String accountId) {
        Optional<AccountJpa> account =  accountJpaRepository.findById(accountId);

        if(account.isPresent()){
            return toAccount(account.get());
        } else {
            throw new NoAccountFoundException();
        }

    }

    @Override
    public Account updateAccount(Account account) {
        return toAccount(accountJpaRepository.save(new AccountJpa(account)));
    }


    private Account toAccount(AccountJpa accountJpa){
        Account account = new Account();
        account.setId(accountJpa.getId());
        account.setFirstname(accountJpa.getFirstname());
        account.setLastname(accountJpa.getLastname());
        account.setFunds(accountJpa.getFunds());
        return account;
    }


}
