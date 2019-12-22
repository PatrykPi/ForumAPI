package com.forum.ForumAPI.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.forum.ForumAPI.model.CustomUserDetails;

public class LoggedUserDetailsImpl implements LoggedUserDetails {
	
	CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	@Override
	public String getUsername() {
		return userDetails.getUsername();
	}

	@Override
	public long getUserId() {
		return userDetails.getUserId();
	}

}
