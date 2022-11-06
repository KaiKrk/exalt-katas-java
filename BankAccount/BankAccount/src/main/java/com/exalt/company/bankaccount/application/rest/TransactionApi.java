package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Transaction;

import java.time.LocalDate;

public class TransactionApi {

    private String id;
    private LocalDate date;
    private String account;
    private Double amount;
    private Boolean isSuccesful;

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean getSuccesful() {
        return isSuccesful;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSuccesful(Boolean succesful) {
        isSuccesful = succesful;
    }

    static TransactionApi toTransactionApi (Transaction transaction){
        TransactionApi transactionApi = new TransactionApi();
        transactionApi.setId(transactionApi.getId());
        transactionApi.setAccount(transaction.getAccount());
        transactionApi.setAmount(transaction.getAmount());
        transactionApi.setDate(transaction.getDate());
        transactionApi.setSuccesful(transaction.getIsSuccesful());
        return transactionApi;
    }

     static Transaction toTransaction (TransactionApi transactionApi){
        Transaction transaction = new Transaction();
        transaction.setId(transactionApi.getId());
        transaction.setAccount(transactionApi.getAccount());
        transaction.setAmount(transactionApi.getAmount());
        transaction.setDate(transactionApi.getDate());
        return transaction;
    }
}
