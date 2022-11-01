package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.UUID;


public class SubmitTransactionTest {


    SubmitTransation submitTransation;

    @Test
    void addTransactionForAccountShouldUpdateItsFunds(){
        //Given
        String accountId = UUID.randomUUID().toString();
        String firstname = "Charles";
        String lastname = "Dupont";
        Double funds = 5000D;

        Account account =  new Account(accountId, firstname,lastname,funds);

        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId,date ,accountId,amount);
        //When
        Account updatedAccount = submitTransation.execute(account, transaction);


        //Then
        assertThat(updatedAccount.getFunds()).isEqualTo(funds+amount);
    }

    @Test
    void withdrawMoreThanFundsShouldRaiseAnError(){
        //Given
        String accountId = UUID.randomUUID().toString();
        String firstname = "Charles";
        String lastname = "Dupont";
        Double funds = 5000D;

        Account account =  new Account(accountId, firstname,lastname,funds);

        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = -10000D;

        Transaction transaction = new Transaction(transactionId,date ,accountId,amount);
        //When
        Account updatedAccount = submitTransation.execute(account, transaction);


        //Then
        assertThrows(IllegalArgumentException.class, () -> submitTransation.execute(account, transaction), "not enough funds");
    }
}
