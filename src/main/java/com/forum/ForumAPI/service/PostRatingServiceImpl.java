package com.forum.ForumAPI.service;

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
	public void setPostLiked(long postId) {

		long currentUserId = authenticatedUserDetails.getUserId();

		PostEntity post = postService.findByIdWithPublicAccess(postId);

		PostRatingEntity postRating = postRatingRepository
				.findByUserIdAndPostId(currentUserId, postId)
				.map(rating -> {
					if (!rating.isLiked()) {
						post.increaseLikes();
						post.decreaseDislikes();
					}
					return rating;
				})
				.orElseGet(() -> {
					PostRatingEntity rating = new PostRatingEntity();	
					rating.setPost(post);
					rating.setUser(authenticatedUserDetails.getUser());
		
					post.increaseLikes();
		
					return rating;
				});
		
		postRating.setLiked();

		postService.save(post);
		postRatingRepository.save(postRating);
	}

	@Override
	public void setPostDisliked(long postId) {

		long currentUserId = authenticatedUserDetails.getUserId();

		PostEntity post = postService.findByIdWithPublicAccess(postId);

		PostRatingEntity postRating = postRatingRepository
				.findByUserIdAndPostId(currentUserId, postId)
				.map(rating -> {
					if (rating.isLiked()) {
						post.decreaseLikes();
						post.increaseDislikes();
					}
					return rating;
				})
				.orElseGet(() -> {
					PostRatingEntity rating = new PostRatingEntity();
					rating.setPost(post);
					rating.setUser(authenticatedUserDetails.getUser());

					post.increaseDislikes();
					
					return rating;
				});
		
		postRating.setDisliked();

		postService.save(post);
		postRatingRepository.save(postRating);
	}

	@Override
	public void deletePostRating(long postId) {

		long currentUserId = authenticatedUserDetails.getUserId();
		
		PostRatingEntity postRating = postRatingRepository
										.findByUserIdAndPostId(currentUserId, postId)
										.orElseThrow(() -> new ResourceNotFoundException("Post with id" + postId + " is not rated"));

		PostEntity post = postService.findById(postId);

		if (postRating.isLiked()) {
			post.decreaseLikes();
		} else {
			post.decreaseDislikes();
		}

		postService.save(post);
		postRatingRepository.delete(postRating);
	}
}
