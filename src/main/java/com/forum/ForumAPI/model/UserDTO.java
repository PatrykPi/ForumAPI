package com.forum.ForumAPI.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	@NotBlank(message="Username is required.")
	@Size(min = 3)
	private String username;
	
	@NotBlank(message="Password is required.")
	@Size(min = 6)
	private String password;
}
