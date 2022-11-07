package com.exalt.company.bankaccount.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.concurrent.Semaphore;
import java.util.zip.DeflaterOutputStream;

public class Account {

    private String id;
    private String firstname;
    private String lastname;
    private Double funds;

    /**
     * Semaphore is used here to assure that withdraw process can only be executed one at a time to ensure proper funds verifying process
     */
    @JsonIgnore
    private Semaphore semaphore = new Semaphore(1);

    public Account(){}

    public Account( String firstname, String lastname, Double funds){
        this.firstname = firstname;
        this.lastname = lastname;
        this.funds = funds;
    }
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

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setFunds(Double funds) {
        this.funds = funds;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setId(String id) {
        this.id = id;
    }


}
