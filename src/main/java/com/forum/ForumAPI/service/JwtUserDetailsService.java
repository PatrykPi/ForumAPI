package com.forum.ForumAPI.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.forum.ForumAPI.dto.UserDTO;
import com.forum.ForumAPI.entity.UserEntity;

public interface JwtUserDetailsService extends UserDetailsService {
	
	public UserEntity save(UserDTO user);
	
	public UserEntity findByUsername(String username);
	
	public UserEntity findById(long userId);
	
	public void update(UserEntity user);
}
