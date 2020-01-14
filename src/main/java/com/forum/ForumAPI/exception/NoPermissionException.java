package com.forum.ForumAPI.exception;

public class NoPermissionException  extends RuntimeException  {

	private static final long serialVersionUID = -4577254322100041048L;

	public NoPermissionException(String msg) {
		super(msg);
	}
	
	public NoPermissionException(String msg, Exception e) {
		super(msg, e);
	}

}
