package com.forum.ForumAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.NoPermissionException;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
import com.forum.ForumAPI.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthenticatedUserDetails authenticatedUserDetails;

	@Override
	public List<PostEntity> findByUserId(long userId){
		return postRepository.findByUserId(userId);
	}
	
	@Override
	public List<PostEntity> findByUserIdWithPublicAccess(long userId) {
		return postRepository.findByUserIdAndIsPublic(userId, true);
	}

	@Override
	public PostEntity findById(long postId) {
		
		PostEntity post = postRepository
							.findById(postId)
							.orElseThrow(() -> new ResourceNotFoundException("Post with id" + postId + "not found"));
		
		if(checkUserPermission(post)) throw new NoPermissionException("You have no permission to get post with id" + postId);
		
		return post;
	}

	@Override
	public void delete(long postId) {
		
		if (!postRepository.existsById(postId)) throw new ResourceNotFoundException("Post with id" + postId + "not found");
	
		postRepository.deleteById(postId);
	}

	@Override
	public void update(long postId, PostEntity post){
		
		PostEntity newPost = findById(postId);
		
		newPost.setTitle(post.getTitle());
		newPost.setText(post.getText());
		newPost.setPublic(post.isPublic());
		
		postRepository.save(newPost);
	}
	
	@Override
	public void save(PostEntity post) {
		postRepository.save(post);
	}

	@Override
	public PostEntity findByIdWithPublicAccess(long postId){
		return postRepository
				.findByIdAndIsPublic(postId, true)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id" + postId + " and public access not found"));
	}

	@Override
	public boolean existsByIdWithPublicAccess(long postId) {
		return postRepository.existsByIdAndIsPublic(postId, true);
	}
	
	private boolean checkUserPermission(PostEntity post) {
		
		long userId = post.getUser().getId();		
		long currentUserId = authenticatedUserDetails.getUserId();
		
		return userId == currentUserId;
	}
}
