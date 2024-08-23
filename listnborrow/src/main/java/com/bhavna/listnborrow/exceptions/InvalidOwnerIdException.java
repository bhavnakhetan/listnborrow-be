package com.bhavna.listnborrow.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOwnerIdException extends RuntimeException {
    public InvalidOwnerIdException(String message) {
        super(message);
    }
}