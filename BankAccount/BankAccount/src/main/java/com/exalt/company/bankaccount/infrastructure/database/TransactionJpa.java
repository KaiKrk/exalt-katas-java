package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Data
public class TransactionJpa {

    @Id
    @Column(name = "transaction_id")
    private String id;
    private LocalDate date;
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountJpa account;
    private Double amount;

    private Boolean isSuccesful;



    TransactionJpa (Transaction transaction){
        this.id = transaction.getId();
        this.date = transaction.getDate();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.isSuccesful = transaction.getIsSuccesful();
    }

    public TransactionJpa() {

    }
}
