package com.forum.ForumAPI.jwt;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenUtil extends Serializable {

	public String getUsernameFromToken(String token);
	
	public Date getExpirationDateFromToken(String token);
	
	public String generateToken(UserDetails userDetails);
	
	public Boolean validateToken(String token, UserDetails userDetails);
}
