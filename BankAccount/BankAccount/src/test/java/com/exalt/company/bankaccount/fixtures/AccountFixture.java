package com.exalt.company.bankaccount.fixtures;

import com.exalt.company.bankaccount.domain.entities.Account;

import java.util.UUID;

public class AccountFixture {

    public static Account aAccount(){
        String id = UUID.randomUUID().toString();
        String firstname = "Carole";
        String lastname = "Dou";
        Double funds = 15000D;

        return new Account(id,firstname,lastname,funds);
    }

    public static Account aNewAccount(){
        String id = UUID.randomUUID().toString();
        String firstname = "Patrick";
        String lastname = "Dou";
        Double funds = 25000D;

        return new Account(id,firstname,lastname,funds);
    }
}
