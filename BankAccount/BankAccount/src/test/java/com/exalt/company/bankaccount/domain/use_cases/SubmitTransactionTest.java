package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class SubmitTransactionTest {

    @Mock
    TransactionPort transactionPort;

    @InjectMocks
    SubmitTransaction submitTransaction;
    Account account;
    LocalDate date = LocalDate.now();

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
        // w stands for working account, a local variable used to be manipulated
        Account wAccount = new Account(account.getId(),account.getFirstname(),account.getLastname(),account.getFunds());
        wAccount.setFunds(5500D);
        doReturn(wAccount).when(transactionPort).updateAccount(account, transaction);
        //When
        Account updatedAccount = transactionPort.updateAccount(account, transaction);

        System.out.println(account.getFunds());
        //Then
        assertThat(updatedAccount.getFunds()).isEqualTo(account.getFunds()+amount);
    }



    @Test
    void withdrawMoreThanFundsShouldRaiseAnError(){
        //Given
        String transactionId = UUID.randomUUID().toString();

        Double amount = -10000D;

        Transaction transaction = new Transaction(transactionId,date ,account.getId(),amount);
        when(transactionPort.updateAccount(account,transaction)).thenThrow(new IllegalArgumentException("Bank Transaction failed : not enough funds "));
        //Then
        assertThrows(IllegalArgumentException.class, () -> transactionPort.updateAccount(account, transaction), "Bank Transaction failed : not enough funds ");
    }
}
