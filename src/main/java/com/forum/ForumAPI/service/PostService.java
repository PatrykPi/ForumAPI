package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.PostEntity;

public interface PostService {
	
	List<PostEntity> findByUserId(long userId);
	
	List<PostEntity> findByUserIdWithPublicAccess(long userId);
	
	PostEntity findById(long postId);
	
	void delete(long postId);
	
	void save(PostEntity post);
	
	void update(long postId, PostEntity post);
	
	PostEntity findByIdWithPublicAccess(long postId);
	
	boolean existsByIdWithPublicAccess(long postId);
}
