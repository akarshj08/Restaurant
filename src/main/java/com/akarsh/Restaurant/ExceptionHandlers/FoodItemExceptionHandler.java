package com.akarsh.Restaurant.ExceptionHandlers;

import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoodItemExceptionHandler
{

    @ExceptionHandler(InvalidIDException.class)
    ResponseEntity<String> handleInvalidException(InvalidIDException ex)
    {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

}