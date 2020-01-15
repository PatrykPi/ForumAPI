package com.forum.ForumAPI.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageResponseBody extends CustomResponse {

	private String message;
	
	public MessageResponseBody() {
		super();
	}
	
	public MessageResponseBody(String message) {
		super();
		this.message = message;
	}
}
