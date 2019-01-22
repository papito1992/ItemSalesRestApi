package com.SalesAPI.SalesAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SalesItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemNotFound(SalesItemNotFoundException ex) {
        return ex.getMessage();
    }
}
