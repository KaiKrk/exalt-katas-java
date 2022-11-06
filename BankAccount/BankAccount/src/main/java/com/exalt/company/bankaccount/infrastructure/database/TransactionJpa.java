package com.exalt.company.bankaccount.infrastructure.database;

import com.exalt.company.bankaccount.domain.entities.TransactionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class TransactionJpa {

    @Id
    @Column(name = "transaction_id")
    private Integer id;
    private LocalDate date;
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountJpa account;
    private Double amount;

    private Boolean isSuccesful;
}
