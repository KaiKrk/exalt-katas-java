package com.exalt.company.bankaccount.application.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountAdapter {

    @PostMapping("/create")
    public void createAccount(@RequestBody AccountApi account){

    }

}
