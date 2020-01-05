package com.forum.ForumAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.model.CustomUserDetails;
import com.forum.ForumAPI.repository.UserRepository;

@Service
public class AuthenticatedUserDetailsImpl implements AuthenticatedUserDetails {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public String getUsername() {
		return getCustomUserDetails().getUsername();
	}

	@Override
	public long getUserId() {
		return getCustomUserDetails().getUserId();
	}
	
	@Override
	public UserEntity getUser() {
		long currentUserId = getUserId();
		return userRepository
				.findById(currentUserId)
				.get();
	}
	
	private CustomUserDetails getCustomUserDetails() {
		return (CustomUserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
	}

}
