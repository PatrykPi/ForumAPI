package com.forum.ForumAPI.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -5738263202009626859L;
	
	private final String jwtToken;
	
	public JwtResponse(String jwttoken) {
		this.jwtToken = jwttoken;
	}
	
	public String getToken() {
		return this.jwtToken;
	}
}
