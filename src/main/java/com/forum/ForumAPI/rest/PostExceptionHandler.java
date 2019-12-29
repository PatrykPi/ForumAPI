package com.forum.ForumAPI.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.model.MessageResponseBody;

@ControllerAdvice
public class PostExceptionHandler {

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<?> handlePostNotFound() {
		
		return ResponseEntity
				.status(404)
				.body(new MessageResponseBody("Post not found"));
	}
}
