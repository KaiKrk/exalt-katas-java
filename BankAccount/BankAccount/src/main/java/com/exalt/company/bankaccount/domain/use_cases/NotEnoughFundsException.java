package com.exalt.company.bankaccount.domain.use_cases;


import net.bytebuddy.implementation.bytecode.Throw;

public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException() {
        super("Bank Transaction failed : not enough funds");
    }
}
