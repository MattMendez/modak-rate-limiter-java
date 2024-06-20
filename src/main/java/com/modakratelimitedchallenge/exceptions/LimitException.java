package com.modakratelimitedchallenge.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LimitException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public LimitException() {
        super("The notification exceed the time and amount limit");
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
