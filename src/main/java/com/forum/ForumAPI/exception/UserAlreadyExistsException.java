package com.forum.ForumAPI.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8908267605792356938L;
	
	public UserAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}
	
	public UserAlreadyExistsException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
