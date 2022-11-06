package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.use_cases.CreateAccount;
import com.exalt.company.bankaccount.domain.use_cases.DepositTransaction;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import com.exalt.company.bankaccount.domain.use_cases.WithdrawTransaction;
import com.exalt.company.bankaccount.fixtures.AccountFixture;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountAdapter.class)
public class AccountAdapterTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepositTransaction depositTransaction;

    @MockBean
    WithdrawTransaction withdrawTransaction;

    @MockBean
    CreateAccount createAccount;

    @MockBean
    RetrieveAccount retrieveAccount;



    @Test
    void getAccount() throws Exception {
        final String accountId = "101";
        Account account = AccountFixture.aAccount();
        Mockito.when(retrieveAccount.execute(accountId)).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account?accountId="+accountId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(account.getId())));
    }
}
