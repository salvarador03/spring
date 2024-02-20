package com.sergio.curso.springboot.error.springbooterror.controllers;

import com.sergio.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.sergio.curso.springboot.error.springbooterror.models.Error;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ErrorHandlerException {
    
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> divisionByZero(Exception ex) {

        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error division por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);

    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(NumberFormatException ex){

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "!Número invalido o incorrecto, no tiene formato de digito!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    
    @ExceptionHandler({NullPointerException.class,
        HttpMessageNotWritableException.class,
        UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(HttpMessageNotWritableException ex){

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "¡El usuario o rol no existe!");
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundEx(NoHandlerFoundException ex) {
        
        Error error = new Error();

        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(404).body(error);
    }
}
