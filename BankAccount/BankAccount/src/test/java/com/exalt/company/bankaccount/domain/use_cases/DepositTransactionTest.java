package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DepositTransactionTest {

    @Mock
    TransactionPort transactionPort;


    @Mock
    DepositTransaction depositTransaction;

    @Mock
    AccountPort accountPort;

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
    public void addDepositForAccountShouldUpdateItsFunds(){
        //Given
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId,date, TransactionType.DEPOSIT ,account.getId(),amount);
        // w stands for working account, a local variable used to be manipulated
        Account wAccount = new Account(account.getId(),account.getFirstname(),account.getLastname(),account.getFunds());
        wAccount.setFunds(5500D);
        doReturn(wAccount).when(depositTransaction).executeDepositTransaction(account.getId(), transaction);
        //When
        Account updatedAccount = depositTransaction.executeDepositTransaction(account.getId(), transaction);
        //Then
        assertThat(updatedAccount.getFunds()).isEqualTo(account.getFunds()+amount);
    }



}
