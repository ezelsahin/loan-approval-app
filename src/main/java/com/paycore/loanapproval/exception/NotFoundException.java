package com.paycore.loanapproval.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {

        super(message + " not found!");
    }
}
