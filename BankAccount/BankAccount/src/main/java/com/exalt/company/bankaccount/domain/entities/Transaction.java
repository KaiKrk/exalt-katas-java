package com.exalt.company.bankaccount.domain.entities;

import java.time.LocalDate;

public class Transaction {

    private String id;
    private LocalDate date;

    private String account;
    private Double amount;

    private Boolean isSuccesful;

    public Transaction (){};

    public Transaction (String id, LocalDate date, String account, Double amount){
        this.id = id;
        this.date = date;
        this.account = account;
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
