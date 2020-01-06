package com.forum.ForumAPI.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostDTO {
	
	@NotBlank(message = "title is required")
	private String title;
	
	@NotBlank(message = "text is required")
	private String text;
	
	private boolean isPublic;
}
