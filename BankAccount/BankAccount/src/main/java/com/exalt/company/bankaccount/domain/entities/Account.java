package com.exalt.company.bankaccount.domain.entities;

import java.math.BigDecimal;
import java.util.zip.DeflaterOutputStream;

public class Account {

    private String id;
    private String firstname;
    private String lastname;
    private Double funds;

    public Account(String id, String firstname, String lastname, Double funds){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.funds = funds;
    }

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

    public void setFunds(Double funds) {
        this.funds = funds;
    }
}
