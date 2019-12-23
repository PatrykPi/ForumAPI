package com.forum.ForumAPI.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseBody {
	
	private LocalDateTime timestamp = LocalDateTime.now();
	
	private List<String> errors;
}
