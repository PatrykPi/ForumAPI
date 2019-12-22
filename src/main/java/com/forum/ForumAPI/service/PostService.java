package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.PostEntity;

public interface PostService {
	
	List<PostEntity> findByUserId(long userId);
	
	PostEntity findById(int postId);
}
