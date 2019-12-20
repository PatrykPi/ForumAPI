package com.forum.ForumAPI.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.service.JwtUserDetailsService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@PostMapping("/users/me/posts")
	public ResponseEntity<?> postPost(@Valid @RequestBody PostEntity post, Authentication authentication){
		
		String currentUserName = authentication.getName();
		
		UserEntity user = jwtUserDetailsService.findByUsername(currentUserName);
		
		user.addPost(post);
		
		jwtUserDetailsService.update(user);
		
		return ResponseEntity.ok(
				user
				.getPosts()
				.get(user.getPosts().size() - 1));
	}
}
