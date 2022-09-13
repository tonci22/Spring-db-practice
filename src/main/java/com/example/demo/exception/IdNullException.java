package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNullException extends RuntimeException {
    public IdNullException() {
        super();
    }

    public IdNullException(String message) {
        super(message);
    }

    public IdNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
