package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import com.exalt.company.bankaccount.domain.use_cases.NotEnoughFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WithdrawTransactionTest {

    @Mock
    AccountPort accountPort;
    @Mock
    TransactionPort transactionPort;
    @Mock
    VerifyTransaction verifyTransaction;

    @Mock
    WithdrawTransaction withdrawTransaction;
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
    void withdrawMoreThanFundsShouldRaiseAnError() throws InterruptedException {
        //Given
        String transactionId = UUID.randomUUID().toString();

        Double amount = -10000D;

        Transaction transaction = new Transaction(transactionId,date, TransactionType.WITHDRAW ,account.getId(),amount);
        when(withdrawTransaction.executeWithdrawTransaction(account, transaction))
                .thenThrow(new NotEnoughFundsException());
        //Then
        assertThrows(NotEnoughFundsException.class, () -> withdrawTransaction.executeWithdrawTransaction(account, transaction), "Bank Transaction failed : not enough funds ");
    }
}
