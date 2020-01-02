package com.forum.ForumAPI.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/{postId}")
	public ResponseEntity<?> getPost(@PathVariable long postId) throws PostNotFoundException{
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);
		
		return ResponseEntity.ok(post);
	}
	
	@PatchMapping("/{postId}")
	public ResponseEntity<?> updatePost(@RequestParam(required = false) boolean isLiked, 
			@RequestParam(required = false) boolean isDisliked){
		
		return null;
		
	}

}
