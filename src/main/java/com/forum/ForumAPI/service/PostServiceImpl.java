package com.forum.ForumAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
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
	public PostEntity findById(long postId) throws PostNotFoundException {
		return this.findByIdOrThrowException(postId);
	}

	@Override
	public void delete(long postId) throws PostNotFoundException {	
		this.findByIdOrThrowException(postId);
		postRepository.deleteById(postId);
	}

	@Override
	public void update(long postId, PostEntity post) throws PostNotFoundException {
		this.findByIdOrThrowException(postId);		
		postRepository.save(post);
	}

	@Override
	public PostEntity findByIdWithPublicAccess(long postId) throws PostNotFoundException {
		return postRepository
				.findByIdAndIsPublic(postId, true)
				.orElseThrow(() -> new PostNotFoundException("Post not Found"));
	}
	
	private PostEntity findByIdOrThrowException(long postId) throws PostNotFoundException {
		return postRepository
				.findById(postId)
				.orElseThrow(() -> new PostNotFoundException("Post not found"));
	}
}
