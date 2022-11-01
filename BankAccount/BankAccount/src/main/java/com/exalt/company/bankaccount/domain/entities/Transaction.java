package com.exalt.company.bankaccount.domain.entities;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private String id;
    private LocalDate date;

    private String account;
    private Double amount;

    private Boolean isSuccesful;

    public Transaction (String id, LocalDate date, String account, Double amount){
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getIsSuccesful() {
        return isSuccesful;
    }
}
