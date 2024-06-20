package com.modakratelimitedchallenge.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorSendingException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public ErrorSendingException() {
        super("Error during sending");
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }
}