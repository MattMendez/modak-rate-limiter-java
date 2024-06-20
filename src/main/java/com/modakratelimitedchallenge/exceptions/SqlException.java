package com.modakratelimitedchallenge.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SqlException extends RuntimeException {

    private String code;

    private HttpStatus status;

    public SqlException() {
        super("SQL error");
        this.code = "500";
        this.status = HttpStatus.BAD_REQUEST;
    }

}