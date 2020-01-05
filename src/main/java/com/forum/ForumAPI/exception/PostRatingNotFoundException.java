package com.forum.ForumAPI.exception;

import javassist.NotFoundException;

public class PostRatingNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -8808046073471830018L;

	public PostRatingNotFoundException(String msg) {
		super(msg);
	}
	
	public PostRatingNotFoundException(String msg, Exception e) {
		super(msg, e);
	}

}
