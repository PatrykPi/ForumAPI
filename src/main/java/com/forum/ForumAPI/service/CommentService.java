package com.forum.ForumAPI.service;

import com.forum.ForumAPI.entity.Comment;
import com.forum.ForumAPI.exception.CommentNotFoundException;
import com.forum.ForumAPI.exception.PostNotFoundException;

public interface CommentService {
	
	void save(Comment comment, long postId) throws PostNotFoundException;

	void update(Comment comment) throws CommentNotFoundException;
}
