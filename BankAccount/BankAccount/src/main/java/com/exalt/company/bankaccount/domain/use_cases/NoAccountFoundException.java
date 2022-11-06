package com.exalt.company.bankaccount.domain.use_cases;

public class NoAccountFoundException extends RuntimeException{

    public NoAccountFoundException() {
        super("No Corresponding Account in our Bank");
    }
}
