package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import com.exalt.company.bankaccount.domain.use_cases.DepositTransaction;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveTransactionHistory;
import com.exalt.company.bankaccount.domain.use_cases.WithdrawTransaction;
import com.exalt.company.bankaccount.fixtures.TransactionFixture;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.*;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionAdapter.class)
public class TransactionAdapterTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepositTransaction depositTransaction;

    @MockBean
    WithdrawTransaction withdrawTransaction;

    @MockBean
    RetrieveAccount retrieveAccount;

    @MockBean
    RetrieveTransactionHistory retrieveTransactionHistory;

    @Test
    void getTransactionHistory() throws Exception{
        final String accountId = "101";
        List<Transaction> transactionList = TransactionFixture.aTransactionList();
        Mockito.when(retrieveTransactionHistory.execute(accountId)).thenReturn(transactionList);

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/history?accountId="+accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));
    }


}
