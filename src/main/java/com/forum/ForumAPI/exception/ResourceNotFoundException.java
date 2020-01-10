package com.forum.ForumAPI.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5061237511221286476L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	public ResourceNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
}
