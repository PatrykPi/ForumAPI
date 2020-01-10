package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.CommentEntity;

public interface CommentService {
	
	CommentEntity findById(long commentId);
	
	void save(CommentEntity comment, long postId);

	void update(CommentEntity comment);
	
	void delete(long commentId);

	List<CommentEntity> findByPostId(long postId);
}
