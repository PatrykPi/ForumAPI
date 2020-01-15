package com.forum.ForumAPI.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CustomResponse {

	private LocalDateTime timestamp;

	public CustomResponse() {
		this.timestamp = LocalDateTime.now();
	}
}
