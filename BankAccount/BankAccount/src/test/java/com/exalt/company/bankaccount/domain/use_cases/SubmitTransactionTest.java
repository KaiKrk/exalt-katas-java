package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;


public class SubmitTransactionTest {


    SubmitTransaction submitTransaction;
    Account account;

    @BeforeEach
    public void init(){
        String accountId = UUID.randomUUID().toString();
        String firstname = "Charles";
        String lastname = "Dupont";
        Double funds = 5000D;
        this.account =  new Account(accountId, firstname,lastname,funds);
    }

    @Test
    public void addTransactionForAccountShouldUpdateItsFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        //When
        Account updatedAccount = submitTransaction.execute(account, transaction);


        //Then
        assertThat(updatedAccount.getFunds()).isEqualTo(account.getFunds()+amount);
    }

    @Test
    void isTrueWhenWithdrawAmountIsLowerThanFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = -500D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        //When
       Boolean isValid = submitTransaction.verifyFunds(account, transaction);

        //Then
        assertThat(isValid).isEqualTo(true);
    }

    @Test
    void isFalseWhenWithdrawAmountIsLowerThanFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = -20000D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        //When
        Boolean isValid = submitTransaction.verifyFunds(account, transaction);

        //Then
        assertThat(isValid).isEqualTo(false);
    }

    @Test
    void withdrawMoreThanFundsShouldRaiseAnError(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = -10000D;

        Transaction transaction = new Transaction(transactionId,date ,accountId,amount);
        //When
        Account updatedAccount = submitTransaction.execute(account, transaction);


        //Then
        assertThrows(IllegalArgumentException.class, () -> submitTransaction.execute(account, transaction), "not enough funds");
    }
}
