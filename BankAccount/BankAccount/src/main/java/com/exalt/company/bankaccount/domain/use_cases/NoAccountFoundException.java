package com.exalt.company.bankaccount.domain.use_cases;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class NoAccountFoundException extends NoSuchElementException {

    public NoAccountFoundException() {
        super("No Corresponding Account in our Bank");
    }
}
