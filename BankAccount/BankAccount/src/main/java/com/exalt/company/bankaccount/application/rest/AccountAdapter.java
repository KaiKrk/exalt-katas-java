package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.use_cases.CreateAccount;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountAdapter {

    private final RetrieveAccount retrieveAccount;

    private final CreateAccount createAccount;

    Logger logger = LoggerFactory.getLogger(AccountAdapter.class);


    @Autowired
    AccountAdapter(RetrieveAccount retrieveAccount, CreateAccount createAccount){
        this.retrieveAccount = retrieveAccount;
        this.createAccount = createAccount;

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody AccountApi account){
        Account returnedAccount  =  createAccount.execute(AccountApi.toAccount(account));
        logger.info("new Account created" );
       return returnedAccount;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@RequestParam String accountId){
        return retrieveAccount.execute(accountId);
    }

}
