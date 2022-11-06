package com.exalt.company.bankaccount.infrastructure.database;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountJPA {

    @Id
    @Column(name = "account_id")
    private String id;
    private String firstname;
    private String lastname;
    private Double funds;

    @OneToMany(mappedBy = "account")
    private List<TransactionJPA> transactions;

}
