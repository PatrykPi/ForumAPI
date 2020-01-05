package com.forum.ForumAPI.rest.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.model.MessageResponseBody;

@ControllerAdvice
public class PostExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<?> handlePostNotFound(RuntimeException ex) {
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponseBody(ex.getMessage()));
	}
}
