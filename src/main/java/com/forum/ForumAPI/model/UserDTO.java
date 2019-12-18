package com.forum.ForumAPI.model;

import com.forum.ForumAPI.validation.UserPassword;
import com.forum.ForumAPI.validation.Username;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	@Username
	private String username;
	
	@UserPassword
	private String password;
}
