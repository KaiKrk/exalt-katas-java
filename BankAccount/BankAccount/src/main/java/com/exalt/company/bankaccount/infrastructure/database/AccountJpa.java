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
