package com.forum.ForumAPI.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.model.MessageResponseBody;
import com.forum.ForumAPI.service.JwtUserDetailsService;
import com.forum.ForumAPI.service.LoggedUserDetails;
import com.forum.ForumAPI.service.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private LoggedUserDetails loggedUserDetails;
	
	@Autowired
	private PostService postService;

	@PostMapping("/users/me/posts")
	public ResponseEntity<?> postPost(@Valid @RequestBody PostEntity post, Authentication authentication){
		
		String currentUserName = loggedUserDetails.getUsername();
		
		UserEntity user = jwtUserDetailsService.findByUsername(currentUserName);
		
		user.addPost(post);
		
		jwtUserDetailsService.update(user);
		
		return ResponseEntity.ok(user
									.getPosts()
									.get(user.getPosts().size() - 1));
	}
	
	@GetMapping("/users/me/posts")
	public ResponseEntity<?> getPosts(){
		
		Long currentUserId = loggedUserDetails.getUserId();
		
		List<PostEntity> posts = postService.findByUserId(currentUserId);
		
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/users/me/posts/{postId}")
	public ResponseEntity<?> getPost(@PathVariable int postId) throws PostNotFoundException{
		
		PostEntity post = postService.findById(postId);
//		
//		if (optPost.get().getUser().getId() != loggedUserDetails.getUserId()) {
//			
//			MessageResponseBody body = new MessageResponseBody();
//			
//			body.setMessage("Access is denied");
//			
//			return ResponseEntity
//					.status(403)
//					.body(body);
//		}
		
		return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/users/me/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId) throws PostNotFoundException{
		
		postService.delete(postId);
		
		MessageResponseBody body = new MessageResponseBody();
		
		body.setMessage("Post was deleted");
		
		return ResponseEntity.ok(body);
	}
	
	@PutMapping("users/me/posts/{postId}")
	public ResponseEntity<?> putPost(@Valid @RequestBody PostEntity post, @PathVariable int postId) throws PostNotFoundException{
		
		postService.update(postId, post);
		
		MessageResponseBody body = new MessageResponseBody();
		
		body.setMessage("Post was updated");
		
		return ResponseEntity.ok(body);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<?> handlePostNotFound() {
		
		MessageResponseBody body = new MessageResponseBody();
		
		body.setMessage("Post not found");
		
		return ResponseEntity
				.status(404)
				.body(body);
		
	}
}
