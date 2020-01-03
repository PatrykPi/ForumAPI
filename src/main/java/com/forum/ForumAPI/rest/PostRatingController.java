package com.forum.ForumAPI.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.exception.PostNotFoundException;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/api/posts/{postId}/ratings")
@CrossOrigin
public class PostRatingController {

	@PostMapping
	public ResponseEntity<?> postRating(@PathVariable long postId,
										@RequestParam(required = false) boolean isLiked,
										@RequestParam(required = false) boolean isDisliked ) throws PostNotFoundException{
		
		log.warning(String.valueOf(isLiked));
		
		log.warning(String.valueOf(isDisliked));
		
		if (isLiked || isDisliked) {
			
			
		}
		
		return null;
	}
}
