package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class VerifyTransactionTest {

    Account account;

    @BeforeEach
    public void init(){
        String accountId = UUID.randomUUID().toString();
        String firstname = "Charles";
        String lastname = "Dupont";
        Double funds = 5000D;
        this.account =  new Account(accountId, firstname,lastname,funds);
    }

    VerifyTransaction verifyTransaction = new VerifyTransaction();

    @Test
    void isTrueWhenWithdrawAmountIsLowerThanFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        //When
        Boolean isValid = verifyTransaction.verifyFunds(account, transaction);

        //Then
        assertThat(isValid).isEqualTo(true);
    }

    @Test
    void isFalseWhenWithdrawAmountIsLowerThanFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 20000D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        //When
        Boolean isValid = verifyTransaction.verifyFunds(account, transaction);

        //Then
        assertThat(isValid).isEqualTo(false);
    }
}
