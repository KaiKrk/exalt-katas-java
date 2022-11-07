package com.exalt.company.bankaccount.domain.use_cases;


import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotEnoughFundsException extends RuntimeException {

    public NotEnoughFundsException() {
        super("Bank Transaction failed : not enough funds");
    }
}
