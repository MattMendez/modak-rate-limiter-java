package com.modakratelimitedchallenge.controllers;

import com.modakratelimitedchallenge.dtos.ExceptionDto;
import com.modakratelimitedchallenge.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ErrorSendingException.class)
    public ResponseEntity<ExceptionDto> errorSendingException(ErrorSendingException ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, ex.getStatus());
    }

    @ExceptionHandler(value = SqlException.class)
    public ResponseEntity<ExceptionDto> sqlException(TopicNotFound ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, ex.getStatus());
    }

    @ExceptionHandler(value = LimitException.class)
    public ResponseEntity<ExceptionDto> limitException(LimitException ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, ex.getStatus());
    }

    @ExceptionHandler(value = TopicNotFound.class)
    public ResponseEntity<ExceptionDto> topicNotFound(TopicNotFound ex){
        ExceptionDto exceptionDto = ExceptionDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, ex.getStatus());
    }

}
