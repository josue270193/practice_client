package com.josue.account.domain.exception;

public class BalanceInsufficientException extends BusinessException {

    private static final String message = "Balances insufficient to do this operation.";

    public BalanceInsufficientException() {
        super(message);
    }

}
