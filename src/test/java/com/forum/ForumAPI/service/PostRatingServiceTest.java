package com.forum.ForumAPI.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.PostRatingEntity;
import com.forum.ForumAPI.repository.PostRatingRepository;

@ExtendWith(MockitoExtension.class)
public class PostRatingServiceTest {
    
    @InjectMocks
    private PostRatingServiceImpl postRatingService;
    
    @Mock
    private PostRatingRepository postRatingRepository;
    
    @Mock
    private PostService postService;
    
	@Mock
	private AuthenticatedUserDetails authenticatedUserDetails;
	
	private static final int LIKES_NUMBER = 1;
	private static final int DISLIKES_NUMBER = 1;
	private static final long POST_ID = 1;
	private static final long USER_ID = 1;
	
	private PostEntity post;
	
	@BeforeEach
	void mock() {
		
		post = new PostEntity();
		post.setPublic(true);
		
		post.increaseDislikes();
		post.increaseLikes();
		
		Mockito
			.when(postService.findByIdWithPublicAccess(POST_ID))
			.thenReturn(post);
	
		Mockito
			.when(authenticatedUserDetails.getUserId())
			.thenReturn(USER_ID);
	}
    
	@Test
	void whenSetPostLiked_thenPostRatingIsLiked() {
		
		PostRatingEntity postRating = new PostRatingEntity();
		postRating.setDisliked();
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.of(postRating));
		
		postRatingService.setPostLiked(POST_ID);
			
		assertThat(postRating.isLiked()).isTrue();
	}
	
	@Test
	void whenSetPostLiked_thenIncreasePostLikeAndDecreaseDislike() {
		
		PostRatingEntity postRating = new PostRatingEntity();
		postRating.setDisliked();
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.of(postRating));
		
		postRatingService.setPostLiked(POST_ID);
		
		assertAll("post",
			() -> assertThat(post.getDislikeCount()).isEqualTo(DISLIKES_NUMBER - 1),
			() -> assertThat(post.getLikeCount()).isEqualTo(LIKES_NUMBER + 1)
		);
	}
	
	@Test
	void whenSetPostLiked_thenIncreasePostLike() {
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.ofNullable(null));
		
		postRatingService.setPostLiked(POST_ID);
		
		assertAll("post",
			() -> assertThat(post.getDislikeCount()).isEqualTo(DISLIKES_NUMBER),
			() -> assertThat(post.getLikeCount()).isEqualTo(LIKES_NUMBER + 1)
		);
	}
	
	@Test 
	void whenSetPostDisliked_thenPostRatingIsDisliked(){
		
		PostRatingEntity postRating = new PostRatingEntity();
		postRating.setLiked();
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.of(postRating));
		
		postRatingService.setPostDisliked(POST_ID);
		
		assertThat(postRating.isLiked()).isFalse();
	}
	
	@Test
	void whenSetPostDisliked_thenDecreasePostLikeAndIncreaseDislike() {
		
		PostRatingEntity postRating = new PostRatingEntity();
		postRating.setLiked();
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.of(postRating));
		
		postRatingService.setPostDisliked(POST_ID);
		
		assertAll("post",
			() -> assertThat(post.getDislikeCount()).isEqualTo(DISLIKES_NUMBER + 1),
			() -> assertThat(post.getLikeCount()).isEqualTo(LIKES_NUMBER - 1)
		);
	}
	
	@Test
	void whenSetPostDisliked_thenIncreasePostDislike() {
		
		Mockito
			.when(postRatingRepository.findByUserIdAndPostId(USER_ID, POST_ID))
			.thenReturn(Optional.ofNullable(null));
		
		postRatingService.setPostDisliked(POST_ID);
		
		assertAll("post",
			() -> assertThat(post.getDislikeCount()).isEqualTo(DISLIKES_NUMBER + 1),
			() -> assertThat(post.getLikeCount()).isEqualTo(LIKES_NUMBER)
		);
	}
}
