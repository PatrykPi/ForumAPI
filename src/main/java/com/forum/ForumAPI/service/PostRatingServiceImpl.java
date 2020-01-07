package com.forum.ForumAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.PostRatingEntity;
import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.exception.PostRatingNotFoundException;
import com.forum.ForumAPI.repository.PostRatingRepository;

@Service
public class PostRatingServiceImpl implements PostRatingService {
	
	@Autowired
	private PostRatingRepository postRatingRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AuthenticatedUserDetails authenticatedUserDetails;

	@Override
	public void setPostLiked(long postId) throws PostNotFoundException {
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);
		
		PostRatingEntity postRating = getPostRatingOrCreate(post);
		
		post.increaseLikes();
		
		postService.save(post);
		
		postRating.setLiked();
		
		postRatingRepository.save(postRating);
	}

	@Override
	public void setPostDisliked(long postId) throws PostNotFoundException {
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);

		PostRatingEntity postRating = getPostRatingOrCreate(post);
		
		post.increaseDislikes();
		
		postService.save(post);
		
		postRating.setDisliked();
		
		postRatingRepository.save(postRating);
	}

	@Override
	public void deletePostRating(long postId) throws PostRatingNotFoundException {
		
		long currentUserId = authenticatedUserDetails.getUserId();
;		
		PostRatingEntity postRating = postRatingRepository
										.findByUserIdAndPostId(currentUserId, postId)
										.orElseThrow(()-> new PostRatingNotFoundException("Post is not rated"));
		
		postRatingRepository.delete(postRating);
	}
	
	private PostRatingEntity getPostRatingOrCreate(PostEntity post) {
		
		long currentUserId = authenticatedUserDetails.getUserId();
		
		PostRatingEntity postRating = postRatingRepository
										.findByUserIdAndPostId(currentUserId, post.getId())
										.orElseGet(()->{
											PostRatingEntity newPostRating =  new PostRatingEntity();
											
											newPostRating.setPost(post);
											newPostRating.setUser(authenticatedUserDetails.getUser());
											
											return newPostRating;
										});
		return postRating;
		
	}
}
