package com.forum.ForumAPI.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.model.MessageResponse;
import com.forum.ForumAPI.service.PostRatingService;

@RestController
@RequestMapping("/api/posts/{postId}/ratings")
@CrossOrigin
public class PostRatingController {
	
	@Autowired
	private PostRatingService postRatingService;

	@PostMapping
	public ResponseEntity<?> postRating(@PathVariable long postId,
										@RequestParam(required = false) boolean isLiked,
										@RequestParam(required = false) boolean isDisliked ){
		HttpStatus httpStatus = HttpStatus.OK;
		
		MessageResponse body = new MessageResponse();
		
		if (isLiked && isDisliked) {
			httpStatus = HttpStatus.BAD_REQUEST;
			body.setMessage("Cannot like and dislike");
		}
		else if (isLiked) {
			postRatingService.setPostLiked(postId);
			body.setMessage("Post is liked");
		}
		else if(isDisliked) {
			postRatingService.setPostDisliked(postId);
			body.setMessage("Post is disliked");
		}
		else {
			httpStatus = HttpStatus.BAD_REQUEST;
			body.setMessage("Choose isLiked or isDisliked ");
		}
		
		return ResponseEntity
				.status(httpStatus)
				.body(body);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deletePostRating(@PathVariable long postId){

		postRatingService.deletePostRating(postId);
		
		return ResponseEntity.ok(new MessageResponse("Post rating was deleted"));
	}
}
