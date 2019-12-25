package com.forum.ForumAPI.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageResponseBody {

	private LocalDateTime timestamp = LocalDateTime.now();
	
	private String message;
}
