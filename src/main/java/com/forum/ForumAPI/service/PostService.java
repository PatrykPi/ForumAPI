package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;

public interface PostService {
	
	List<PostEntity> findByUserId(long userId);
	
	List<PostEntity> findByUserIdWithPublicAccess(long userId);
	
	PostEntity findById(long postId) throws PostNotFoundException;
	
	void delete(long postId) throws PostNotFoundException;
	
	void save(PostEntity post);
	
	void update(long postId, PostEntity post) throws PostNotFoundException;
	
	PostEntity findByIdWithPublicAccess(long postId) throws PostNotFoundException;
	
	boolean existsByIdWithPublicAccess(long postId);
}
