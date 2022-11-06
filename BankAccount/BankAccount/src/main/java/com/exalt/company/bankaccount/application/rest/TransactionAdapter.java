package com.exalt.company.bankaccount.application.rest;

import com.exalt.company.bankaccount.domain.entities.Account;
import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.use_cases.DepositTransaction;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveAccount;
import com.exalt.company.bankaccount.domain.use_cases.RetrieveTransactionHistory;
import com.exalt.company.bankaccount.domain.use_cases.WithdrawTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public void depositTransaction(@RequestBody String accountId, TransactionApi transactionApi){
        depositTransaction.executeDepositTransaction(accountId, TransactionApi.toTransaction(transactionApi));
    }

    public void withdrawTransaction(@RequestBody String accountId, TransactionApi transactionApi){
        withdrawTransaction.executeWithdrawTransaction(retrieveAccount.execute(accountId), TransactionApi.toTransaction(transactionApi));
    }

    public List<TransactionApi> getTransactionHistory(@RequestBody AccountApi accountApi){
        return retrieveTransactionHistory.execute(accountApi.getId()).stream().map(transaction -> TransactionApi.toTransactionApi(transaction)).collect(Collectors.toList());
    }


}
