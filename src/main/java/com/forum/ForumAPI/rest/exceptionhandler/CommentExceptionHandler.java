package com.forum.ForumAPI.rest.exceptionhandler;

import java.security.AccessControlException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.forum.ForumAPI.exception.CommentNotFoundException;
import com.forum.ForumAPI.model.MessageResponseBody;

@ControllerAdvice
public class CommentExceptionHandler {
	
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<?> handleCommentNotFound(RuntimeException ex) {
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new MessageResponseBody(ex.getMessage()));
	}
	
	@ExceptionHandler(AccessControlException.class)
	public ResponseEntity<?> handleNoAccess(RuntimeException ex) {
		
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(new MessageResponseBody(ex.getMessage()));
	}
}
