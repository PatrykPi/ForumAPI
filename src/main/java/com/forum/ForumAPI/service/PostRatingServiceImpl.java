package com.forum.ForumAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.PostRatingEntity;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
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
	public void setPostLiked(long postId){
		
		long currentUserId = authenticatedUserDetails.getUserId();
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);

		Optional<PostRatingEntity> postRatingOpt = postRatingRepository.findByUserIdAndPostId(currentUserId, postId);
		
		PostRatingEntity postRating;
		
		if (postRatingOpt.isPresent()) {
			
			postRating = postRatingOpt.get();
			
			if (!postRating.isLiked()) {
				post.increaseLikes();
				post.decreaseDislikes();
			}
		}
		else {
			postRating =  new PostRatingEntity();
			
			postRating.setPost(post);
			postRating.setUser(authenticatedUserDetails.getUser());
			
			post.increaseLikes();
		}
		
		postService.save(post);
		
		postRating.setLiked();
		
		postRatingRepository.save(postRating);
	}

	@Override
	public void setPostDisliked(long postId){
		
		long currentUserId = authenticatedUserDetails.getUserId();
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);

		Optional<PostRatingEntity> postRatingOpt = postRatingRepository.findByUserIdAndPostId(currentUserId, postId);
		
		PostRatingEntity postRating;
		
		if (postRatingOpt.isPresent()) {
			
			postRating = postRatingOpt.get();
			
			if (postRating.isLiked()) {
				post.decreaseLikes();
				post.increaseDislikes();
			}
		}
		else {
			postRating =  new PostRatingEntity();
			
			postRating.setPost(post);
			postRating.setUser(authenticatedUserDetails.getUser());
			
			post.increaseDislikes();
		}
		
		postService.save(post);
		
		postRating.setDisliked();
		
		postRatingRepository.save(postRating);
	}

	@Override
	public void deletePostRating(long postId) {
		
		long currentUserId = authenticatedUserDetails.getUserId();
;		
		PostRatingEntity postRating = postRatingRepository
										.findByUserIdAndPostId(currentUserId, postId)
										.orElseThrow(()-> new ResourceNotFoundException("Post is not rated"));
		
		PostEntity post = postService.findById(postId);
		
		if (postRating.isLiked()) {
			post.decreaseLikes();
		}
		else {
			post.decreaseDislikes();
		}
		
		postService.save(post);
		
		postRatingRepository.delete(postRating);
	}
}
