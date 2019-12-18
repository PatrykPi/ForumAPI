package com.forum.ForumAPI.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = -1635339468072743173L;
	
	@NotBlank(message = "Username is required.")
	@Size(min = 3)
	private String username;
	
	@NotBlank(message = "Password is required.")
	@Size(min = 6)
	private String password;
}
