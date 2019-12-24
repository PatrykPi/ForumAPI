package com.forum.ForumAPI.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.ForumAPI.model.ErrorResponseBody;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = -8921155984364483551L;
	
	@Autowired
	private ObjectMapper objMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		
		ErrorResponseBody body = new ErrorResponseBody();
		
		List<String> errors = new ArrayList<>();
		
		errors.add(authException.getMessage());
		
		body.setErrors(errors);
		
		String bodyJsonString = this.objMapper.writeValueAsString(body);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        response.getWriter().print(bodyJsonString);
	}
}
