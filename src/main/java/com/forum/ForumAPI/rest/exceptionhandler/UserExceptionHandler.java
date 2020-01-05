package com.forum.ForumAPI.rest.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.forum.ForumAPI.exception.UserAlreadyExistsException;
import com.forum.ForumAPI.model.MessageResponseBody;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(RuntimeException ex) {
    	
    	return ResponseEntity
    			.badRequest()
    			.body(new MessageResponseBody(ex.getMessage()));
    }
}
