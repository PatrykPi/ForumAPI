package com.forum.ForumAPI.rest;

import java.util.List;

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

import com.forum.ForumAPI.entity.Comment;
import com.forum.ForumAPI.model.MessageResponseBody;
import com.forum.ForumAPI.service.CommentService;

@RestController
@RequestMapping("api/posts/{postId}/comments")
@CrossOrigin
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public ResponseEntity<?> postComment(@PathVariable long postId, @RequestBody Comment comment){
		
		commentService.save(comment, postId);
		
		return ResponseEntity.ok(new MessageResponseBody("Comment was posted"));
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<?> getComment(@PathVariable long commentId){	
		
		Comment comment = commentService.findById(commentId);
		
		return ResponseEntity.ok(comment);
	}
	
	@GetMapping
	public ResponseEntity<?> getComments(@PathVariable long postId, @PathVariable long commentId){
		
		List<Comment> comments = commentService.findByPostId(postId);
		
		return ResponseEntity.ok(comments);
	}
	
	public ResponseEntity<?> deleteComment(@PathVariable long postId, @PathVariable long commentId){
		
		
		
		return null;	
	}
	
	@PatchMapping("/{commentId}")
	public ResponseEntity<?> patchComment(@PathVariable long commentId, @RequestBody Comment comment){
		commentService.update(comment);
		
		return ResponseEntity.ok(new MessageResponseBody("Comment was updated"));
	}
}
