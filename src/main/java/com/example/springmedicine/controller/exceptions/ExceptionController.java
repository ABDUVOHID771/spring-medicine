package com.example.springmedicine.controller.exceptions;

import com.example.springmedicine.exception.NotFoundException;
import com.example.springmedicine.exception.ResourceNotFoundException;
import com.example.springmedicine.exception.UsernameAlreadyExists;
import com.example.springmedicine.exception.UsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleUsernameDuplicatedException(UsernameException usernameAlreadyExists, WebRequest webRequest) {
        UsernameAlreadyExists exists = new UsernameAlreadyExists(usernameAlreadyExists.getMessage());
        return new ResponseEntity<>(exists, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        NotFoundException notFoundException = new NotFoundException(resourceNotFoundException.getMessage());
        return new ResponseEntity<>(notFoundException, HttpStatus.BAD_REQUEST);
    }

}
