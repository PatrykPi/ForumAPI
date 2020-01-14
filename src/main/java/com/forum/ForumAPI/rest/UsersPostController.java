package com.forum.ForumAPI.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.model.MessageResponseBody;
import com.forum.ForumAPI.service.JwtUserDetailsService;
import com.forum.ForumAPI.service.AuthenticatedUserDetails;
import com.forum.ForumAPI.service.PostService;

@RestController
@RequestMapping("/api/users/me/posts")
@CrossOrigin
public class UsersPostController {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private AuthenticatedUserDetails authenticatedUserDetails;
	
	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<?> postPost(@Valid @RequestBody PostEntity post){
		
		String currentUserName = authenticatedUserDetails.getUsername();
		
		UserEntity user = jwtUserDetailsService.findByUsername(currentUserName);
		
		user.addPost(post);
		
		jwtUserDetailsService.update(user);
		
		return ResponseEntity.ok(user
									.getPosts()
									.get(user.getPosts().size() - 1));
	}
	
	@GetMapping
	public ResponseEntity<?> getPosts(){
		
		Long currentUserId = authenticatedUserDetails.getUserId();
		
		List<PostEntity> posts = postService.findByUserId(currentUserId);
		
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<?> getPost(@PathVariable long postId){
		
		PostEntity post = postService.findById(postId);
		
		return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable long postId){
		
		postService.delete(postId);
		
		return ResponseEntity.ok(new MessageResponseBody("Post was deleted"));
	}
	
	@PatchMapping("/{postId}")
	public ResponseEntity<?> patchPost(@Valid @RequestBody PostEntity post, @PathVariable long postId){
		
		postService.update(postId, post);
		
		return ResponseEntity.ok(new MessageResponseBody("Post was updated"));
	}
}
