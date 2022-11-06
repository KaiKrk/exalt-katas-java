package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.use_cases.DepositTransaction;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveTransactionHistory;
import com.exalt.company.bankaccount.domain.use_cases.WithdrawTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionAdapter {

    private final RetrieveTransactionHistory retrieveTransactionHistory;
    private final WithdrawTransaction withdrawTransaction;
    private final DepositTransaction depositTransaction;

    private final RetrieveAccount retrieveAccount;

    @Autowired
    public TransactionAdapter (RetrieveTransactionHistory retrieveTransactionHistory,WithdrawTransaction withdrawTransaction,DepositTransaction depositTransaction, RetrieveAccount retrieveAccount){
        this.retrieveTransactionHistory = retrieveTransactionHistory;
        this.withdrawTransaction = withdrawTransaction;
        this.depositTransaction = depositTransaction;
        this.retrieveAccount = retrieveAccount;

    }


    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void depositTransaction(@RequestBody String accountId, TransactionApi transactionApi){
        depositTransaction.executeDepositTransaction(accountId, TransactionApi.toTransaction(transactionApi));
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public void withdrawTransaction(@RequestBody String accountId, TransactionApi transactionApi){
        withdrawTransaction.executeWithdrawTransaction(retrieveAccount.execute(accountId), TransactionApi.toTransaction(transactionApi));
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionApi> getTransactionHistory(@RequestParam String accountId){
        return retrieveTransactionHistory.execute(accountId).stream().map(transaction -> TransactionApi.toTransactionApi(transaction)).collect(Collectors.toList());
    }


}
