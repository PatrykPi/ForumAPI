package com.forum.ForumAPI.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.forum.ForumAPI.exception.UserAlreadyExistsException;
import com.forum.ForumAPI.model.ErrorResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorResponseBody body = new ErrorResponseBody();
        
        List<String> errors = ex
        		.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.setErrors(errors);

        return ResponseEntity
        		.status(status)
        		.headers(headers)
        		.body(body);
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    	
    	ErrorResponseBody body = new ErrorResponseBody();
    	
    	List<String> errors = new ArrayList<>();
    	
    	errors.add(ex.getMessage());
    	
        body.setErrors(errors);
    	
    	return ResponseEntity
    			.badRequest()
    			.body(body);
    }
}
