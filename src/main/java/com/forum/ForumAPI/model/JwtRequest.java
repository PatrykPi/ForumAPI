package com.forum.ForumAPI.model;

import java.io.Serializable;

import com.forum.ForumAPI.validation.UserPassword;
import com.forum.ForumAPI.validation.Username;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = -1635339468072743173L;
	
	@Username
	private String username;
	
	@UserPassword
	private String password;
}
