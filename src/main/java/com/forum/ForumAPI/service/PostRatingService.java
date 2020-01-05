package com.forum.ForumAPI.service;

import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.exception.PostRatingNotFoundException;

public interface PostRatingService {
	
	void setPostLiked(long postId)  throws PostNotFoundException;
	
	void setPostDisliked(long postId) throws PostNotFoundException;
	
	void deletePostRating(long postId) throws PostRatingNotFoundException;
}
