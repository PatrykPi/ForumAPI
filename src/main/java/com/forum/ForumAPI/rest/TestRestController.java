package com.forum.ForumAPI.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController {
	
	@GetMapping("/helloWorld")
	public String test() {
		return "Hello World!";
	}

}
