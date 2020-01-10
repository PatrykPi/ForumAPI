package com.forum.ForumAPI.service;

public interface PostRatingService {
	
	void setPostLiked(long postId);
	
	void setPostDisliked(long postId);
	
	void deletePostRating(long postId);
}
