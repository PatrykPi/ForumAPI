package com.forum.ForumAPI.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUserDetails extends User {

	private static final long serialVersionUID = -3696357702237051083L;
	
	private int userId;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId) {
		super(username, password, authorities);
		this.userId = userId;
	}

}
