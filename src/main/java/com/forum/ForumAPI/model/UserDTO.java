package com.forum.ForumAPI.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTO {
	
	@NotBlank(message="Username is required.")
	@Size(min = 3)
	private String username;
	
	@NotBlank(message="Password is required.")
	@Size(min = 6)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
