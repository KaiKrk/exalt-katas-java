package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.Semaphore;

@Entity
@Table(name = "account")
@Data
public class AccountJpa {

    @Id
    @Column(name = "account_id")
    private String id;
    private String firstname;
    private String lastname;
    private Double funds;

    @OneToMany(mappedBy = "account")
    private List<TransactionJpa> transactions;


    AccountJpa(Account account){
        this.id = account.getId();
        this.firstname = account.getFirstname();
        this.lastname = account.getLastname();
        this.funds = account.getFunds();
    }

    public AccountJpa() {

    }


}
