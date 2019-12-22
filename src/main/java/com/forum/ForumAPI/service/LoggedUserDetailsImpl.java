package com.forum.ForumAPI.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.model.CustomUserDetails;

@Service
public class LoggedUserDetailsImpl implements LoggedUserDetails {

	@Override
	public String getUsername() {
		return this.getCustomUserDetails().getUsername();
	}

	@Override
	public long getUserId() {
		return this.getCustomUserDetails().getUserId();
	}
	
	private CustomUserDetails getCustomUserDetails() {
		return (CustomUserDetails) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
	}

}
