package com.forum.ForumAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.PostRatingEntity;
import com.forum.ForumAPI.entity.UserEntity;

@DataJpaTest
public class PostRatingRepositoryTest {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRespository;
	
	@Autowired
	private PostRatingRepository postRatingRepository;
	
	@Test
	void whenFindByUserIdAndPostId_thenFindPostRating() {
		
		UserEntity user = new UserEntity();
		user.setUsername("TestRating");
		user.setPassword("TestRating123");
		userRepository.save(user);
		
		PostEntity post = new PostEntity();
		post.setTitle("Text Text Text");
		post.setText("Text Text Text");
		postRespository.save(post);
		
		PostRatingEntity postRating = new PostRatingEntity();		
		postRating.setPost(post);
		postRating.setUser(user);		
		postRatingRepository.save(postRating);
		
		PostRatingEntity foundPostRating = postRatingRepository
											.findByUserIdAndPostId(user.getId(), post.getId())
											.orElse(null);
		assertAll("foundPostRating",
			() -> assertThat(foundPostRating).isNotNull(),
			() -> assertThat(foundPostRating.getId()).isEqualTo(postRating.getId())
		);
	}
}
