package com.exalt.company.bankaccount.domain.use_cases;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RetrieveTransactionHistoryTest {


    @Mock
    TransactionPort transactionPort;
    @InjectMocks
    RetrieveTransactionHistory retrieveTransactionHistory;

    @Test
    void getAllTransactionHistory(){
        //Given
        String accountId = UUID.randomUUID().toString();
        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = +1500D;
        Boolean isSuccessful = true;
        List<Transaction> transactionList = Lists.newArrayList(new Transaction(transactionId,date,accountId,amount));
        doReturn(transactionList).when(transactionPort).getHistory(accountId);

        //When
        List<Transaction> results = retrieveTransactionHistory.execute(accountId);

        //Then
        assertThat(results).containsExactlyElementsOf(transactionList);
    }
}
