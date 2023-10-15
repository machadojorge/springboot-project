package com.miguel.spring.project.springbootproject.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.miguel.spring.project.springbootproject.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice  // this annotation informs and intercept the ocurrent exceptions for this object can trated this exceptions
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // this annotation informs this method goes intercept any exception with this name "ResourceNotFound"
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)
    {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);

    }
    
}
