package com.forum.ForumAPI.exception;

import javassist.NotFoundException;

public class PostNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 5061237511221286476L;
	
	public PostNotFoundException(String msg) {
		super(msg);
	}
	
	public PostNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
}
