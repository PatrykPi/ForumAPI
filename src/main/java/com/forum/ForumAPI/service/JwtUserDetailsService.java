package com.forum.ForumAPI.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.model.UserDTO;

public interface JwtUserDetailsService extends UserDetailsService {
	
	public UserEntity save(UserDTO user);
}
