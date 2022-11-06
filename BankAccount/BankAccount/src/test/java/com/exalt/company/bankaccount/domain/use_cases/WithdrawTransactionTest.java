package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class WithdrawTransactionTest {

    @Mock
    TransactionPort transactionPort;
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
