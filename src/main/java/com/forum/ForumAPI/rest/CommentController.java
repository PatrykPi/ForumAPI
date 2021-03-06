package com.forum.ForumAPI.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.entity.CommentEntity;
import com.forum.ForumAPI.model.MessageResponse;
import com.forum.ForumAPI.service.CommentService;

@RestController
@RequestMapping("api/posts/{postId}/comments")
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public ResponseEntity<?> postComment(@PathVariable long postId, @Valid @RequestBody CommentEntity comment){
		
		commentService.save(comment, postId);
		
		return ResponseEntity.ok(comment);
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<?> getComment(@PathVariable long commentId){	
		
		CommentEntity comment = commentService.findById(commentId);
		
		return ResponseEntity.ok(comment);
	}
	
	@GetMapping
	public ResponseEntity<?> getComments(@PathVariable long postId){
		
		List<CommentEntity> comments = commentService.findByPostId(postId);
		
		return ResponseEntity.ok(comments);
	}
	
	public ResponseEntity<?> deleteComment(@PathVariable long commentId){
		
		commentService.delete(commentId);
		
		return ResponseEntity.ok(new MessageResponse("Comment was deleted"));	
	}
	
	@PatchMapping
	public ResponseEntity<?> patchComment(@Valid @RequestBody CommentEntity comment){
		
		commentService.update(comment);
		
		return ResponseEntity.ok(new MessageResponse("Comment was updated"));
	}
}
