package com.exalt.company.bankaccount.domain.use_cases;

import java.util.NoSuchElementException;

public class NoAccountFoundException extends NoSuchElementException {

    public NoAccountFoundException() {
        super("No Corresponding Account in our Bank");
    }
}
