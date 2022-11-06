package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Data
public class AccountJpa {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private Double funds;

    @OneToMany(mappedBy = "account")
    private List<TransactionJpa> transactions;

    AccountJpa(Account account){
        this.firstname = account.getFirstname();
        this.lastname = account.getLastname();
        this.funds = account.getFunds();
    }

    public AccountJpa() {

    }

    static Account toAccount(AccountJpa accountJpa){
        Account account = new Account();
        account.setId(accountJpa.getId().toString());
        account.setFirstname(accountJpa.getFirstname());
        account.setLastname(accountJpa.getLastname());
        account.setFunds(accountJpa.getFunds());
        return account;
    }
}
