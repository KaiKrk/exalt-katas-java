package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;

public class AccountApi {

    private String id;
    private String firstname;
    private String lastname;
    private Double funds;

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Double getFunds() {
        return funds;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }


    static AccountApi toAccountApi(Account account){
        AccountApi accountApi = new AccountApi();
        accountApi.setId(account.getId());
        accountApi.setFirstname(accountApi.getFirstname());
        accountApi.setLastname(accountApi.getLastname());
        accountApi.setFunds(accountApi.getFunds());

        return accountApi;
    }
}
