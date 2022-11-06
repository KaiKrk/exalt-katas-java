package com.exalt.company.bankaccount.fixtures;

import com.exalt.company.bankaccount.domain.entities.Transaction;
import com.exalt.company.bankaccount.domain.entities.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionFixture {

    public static List<Transaction> aTransactionList() {

        List<Transaction> transactionList = new ArrayList<>();

        LocalDate aRandomDate = LocalDate.of(2022, 10, 8);
        LocalDate aSecondRandomDate = LocalDate.of(2022, 6, 1);
        String accountId = "101";

        Transaction transactionOne = new Transaction(UUID.randomUUID().toString(), aRandomDate, TransactionType.DEPOSIT , accountId, 500D, true);
        Transaction transactionTwo = new Transaction(UUID.randomUUID().toString(), aSecondRandomDate, TransactionType.WITHDRAW , accountId, 100000D, false);
        Transaction transactionThree = new Transaction(UUID.randomUUID().toString(), aSecondRandomDate, TransactionType.WITHDRAW , accountId, 1500D, true);

        transactionList.add(transactionOne);
        transactionList.add(transactionTwo);
        transactionList.add(transactionThree);

        return transactionList;
    }
}
