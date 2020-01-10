package com.forum.ForumAPI.service;

import java.util.List;

import com.forum.ForumAPI.entity.Comment;

public interface CommentService {
	
	Comment findById(long commentId);
	
	void save(Comment comment, long postId);

	void update(Comment comment);
	
	void delete(long commentId);

	List<Comment> findByPostId(long postId);
}
