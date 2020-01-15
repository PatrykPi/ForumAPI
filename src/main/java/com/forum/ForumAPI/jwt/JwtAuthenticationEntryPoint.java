package com.forum.ForumAPI.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.ForumAPI.model.MessageResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = -8921155984364483551L;
	
	@Autowired
	private ObjectMapper objMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		
		MessageResponse body = new MessageResponse();
		
		body.setMessage(authException.getMessage());
		
		String bodyJsonString = this.objMapper.writeValueAsString(body);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        response.getWriter().print(bodyJsonString);
	}
}
