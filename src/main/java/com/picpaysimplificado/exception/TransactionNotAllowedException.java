package com.picpaysimplificado.exception;

public class TransactionNotAllowedException extends RuntimeException {

    public TransactionNotAllowedException(String message) {
        super(message);
    }

}