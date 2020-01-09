package com.forum.ForumAPI.exception;

import javassist.NotFoundException;

public class CommentNotFoundException extends NotFoundException {
	
	private static final long serialVersionUID = 6058343122999017282L;

	public CommentNotFoundException(String msg) {
		super(msg);
	}
	
	public CommentNotFoundException(String msg, Exception e) {
		super(msg, e);
	}
}
