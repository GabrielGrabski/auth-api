package com.example.authapi.comum.exception.controller;

import com.example.authapi.comum.exception.model.Message;
import com.example.authapi.comum.exception.model.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidacaoException.class)
    public Message validacaoExHandler(ValidacaoException ex) {
        return new Message(ex.getMessage());
    }
}
