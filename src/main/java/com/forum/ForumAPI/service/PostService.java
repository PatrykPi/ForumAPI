package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;

public interface PostService {
	
	List<PostEntity> findByUserId(long userId);
	
	PostEntity findById(int postId) throws PostNotFoundException;
	
	void delete(int postId) throws PostNotFoundException;	
	
	void update(int postId, PostEntity post) throws PostNotFoundException;
}
