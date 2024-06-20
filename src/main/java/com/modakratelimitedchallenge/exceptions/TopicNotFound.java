package com.modakratelimitedchallenge.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TopicNotFound extends RuntimeException {

    private String code;

    private HttpStatus status;

    public TopicNotFound() {
        super("The topic is not supported");
        this.code = "400";
        this.status = HttpStatus.BAD_REQUEST;
    }

}
