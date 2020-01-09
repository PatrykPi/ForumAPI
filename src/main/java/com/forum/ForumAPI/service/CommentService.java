package com.forum.ForumAPI.service;

import java.security.AccessControlException;
import java.util.List;

import com.forum.ForumAPI.entity.Comment;
import com.forum.ForumAPI.exception.CommentNotFoundException;
import com.forum.ForumAPI.exception.PostNotFoundException;

public interface CommentService {
	
	Comment findById(long commentId) throws CommentNotFoundException;
	
	void save(Comment comment, long postId) throws PostNotFoundException;

	void update(Comment comment) throws CommentNotFoundException;
	
	void delete(long commentId) throws CommentNotFoundException, AccessControlException;

	List<Comment> findByPostId(long postId) throws PostNotFoundException;
}
