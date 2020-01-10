package com.forum.ForumAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
import com.forum.ForumAPI.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	private static final String POST_NOT_FOUND_EXCEPTION_MESSAGE = "Post not found";
	
	@Autowired
	private PostRepository postRepository;

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
		return postRepository
				.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND_EXCEPTION_MESSAGE));
	}

	@Override
	public void delete(long postId) {
		
		if (!postRepository.existsById(postId)) throw new ResourceNotFoundException(POST_NOT_FOUND_EXCEPTION_MESSAGE);
	
		postRepository.deleteById(postId);
	}

	@Override
	public void update(long postId, PostEntity post){
		
		PostEntity newPost = postRepository
								.findById(postId)
								.orElseThrow(()-> new ResourceNotFoundException(POST_NOT_FOUND_EXCEPTION_MESSAGE));
		
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
				.orElseThrow(() -> new ResourceNotFoundException(POST_NOT_FOUND_EXCEPTION_MESSAGE));
	}

	@Override
	public boolean existsByIdWithPublicAccess(long postId) {
		// TODO Auto-generated method stub
		return false;
	}
}
