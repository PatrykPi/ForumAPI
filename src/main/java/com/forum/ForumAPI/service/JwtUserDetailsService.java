package com.forum.ForumAPI.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.UserAlreadyExistsException;
import com.forum.ForumAPI.model.UserDTO;

public interface JwtUserDetailsService extends UserDetailsService {
	
	public UserEntity save(UserDTO user) throws UserAlreadyExistsException;
	
	public UserEntity findByUsername(String username);
	
	public void update(UserEntity user);
}
