package com.forum.ForumAPI.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api")
public class TestRestController {
	
	@GetMapping("/test")
	public String test() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		log.warning(authentication.toString());
		
		return "Hello World!";
	}
}
