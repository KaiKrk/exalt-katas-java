package com.exalt.company.bankaccount.domain.entities;

import java.time.LocalDate;

public class Transaction {

    private String id;
    private LocalDate date;
    private TransactionType type;

    private String account;
    private Double amount;

    private Boolean isSuccesful;

    public Transaction (){};

    public Transaction (String id, LocalDate date,TransactionType type, String account, Double amount){
        this.id = id;
        this.date = date;
        this.type = type;
        this.account = account;
        this.amount = amount;
    }

    public Transaction (String id, LocalDate date,TransactionType type ,String account, Double amount, Boolean isSuccesful){
        this.id = id;
        this.date = date;
        this.type = type;
        this.account = account;
        this.amount = amount;
        this.isSuccesful = isSuccesful;
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

    public void setSuccesful(Boolean succesful) {
        isSuccesful = succesful;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
