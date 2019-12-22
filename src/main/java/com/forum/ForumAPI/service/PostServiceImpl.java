package com.forum.ForumAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<PostEntity> findByUserId(long userId) {
		return postRepository.findByUserId(userId);
	}

	@Override
	public PostEntity findById(int postId) {
		return postRepository
				.findById(postId)
				.orElseThrow(RuntimeException::new);
	}

}
