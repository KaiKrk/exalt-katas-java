package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;
import com.exalt.company.bankaccount.domain.use_cases.DepositTransaction;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveTransactionHistory;
import com.exalt.company.bankaccount.domain.use_cases.WithdrawTransaction;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import com.exalt.company.bankaccount.fixtures.TransactionFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.tomcat.util.json.JSONParser;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionAdapter.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @MockBean
    AccountApi accountApi;

    ObjectMapper objectMapper;

    @BeforeAll
    void init(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

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

    @Test
    void postDepositTransaction() throws Exception{

        Account account = AccountFixture.aAccount();

        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId,date, TransactionType.DEPOSIT ,account.getId(),amount);


        Mockito.when(depositTransaction.executeDepositTransaction(account, transaction)).thenReturn(AccountApi.toAccountApi(account));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/deposit?accountId="+account.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void postWithdrawTransaction() throws Exception {
        Account account = AccountFixture.aAccount();

        String transactionId = UUID.randomUUID().toString();
        LocalDate date = LocalDate.now();
        Double amount = 500D;

        Transaction transaction = new Transaction(transactionId, date, TransactionType.WITHDRAW, account.getId(), amount);

        Mockito.when(depositTransaction.executeDepositTransaction(account, transaction)).thenReturn(AccountApi.toAccountApi(account));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/withdraw?accountId=" + account.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
