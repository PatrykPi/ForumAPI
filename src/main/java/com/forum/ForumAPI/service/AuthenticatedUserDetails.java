package com.forum.ForumAPI.service;

import com.forum.ForumAPI.entity.UserEntity;

public interface AuthenticatedUserDetails {

	String getUsername();
	
	long getUserId();
	
	UserEntity getUser();
}
