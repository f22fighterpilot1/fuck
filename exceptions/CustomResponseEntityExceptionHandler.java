package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectidException ex, WebRequest request){
        ProjectidExceptionResponse exceptionResponse = new ProjectidExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.CREATED);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleFileNameException(CheckFileidException ex, WebRequest request){
        CheckFileidExceptionResponse exceptionResponse = new CheckFileidExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.CREATED);
    }

}
