package com.forum.ForumAPI.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/users/me/posts")
@CrossOrigin
public class UsersPostController {
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private LoggedUserDetails loggedUserDetails;
	
	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<?> postPost(@Valid @RequestBody PostEntity post, Authentication authentication){
		
		String currentUserName = loggedUserDetails.getUsername();
		
		UserEntity user = jwtUserDetailsService.findByUsername(currentUserName);
		
		user.addPost(post);
		
		jwtUserDetailsService.update(user);
		
		return ResponseEntity.ok(user
									.getPosts()
									.get(user.getPosts().size() - 1));
	}
	
	@GetMapping
	public ResponseEntity<?> getPosts(){
		
		Long currentUserId = loggedUserDetails.getUserId();
		
		List<PostEntity> posts = postService.findByUserId(currentUserId);
		
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<?> getPost(@PathVariable long postId) throws PostNotFoundException{
		
		PostEntity post = postService.findById(postId);
		
		if (post.getUser().getId() != loggedUserDetails.getUserId()) {
			return ResponseEntity
					.status(403)
					.body(new MessageResponseBody("Access is denied"));
		}
		
		return ResponseEntity.ok(post);
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable long postId) throws PostNotFoundException{
		
		postService.delete(postId);
		
		return ResponseEntity.ok(new MessageResponseBody("Post was deleted"));
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<?> putPost(@Valid @RequestBody PostEntity post, @PathVariable long postId) throws PostNotFoundException{
		
		postService.update(postId, post);
		
		return ResponseEntity.ok(new MessageResponseBody("Post was updated"));
	}
}
