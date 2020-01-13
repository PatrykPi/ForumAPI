package com.forum.ForumAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;

@DataJpaTest
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void whenFindByUserId_thenFindPost() {
		
		UserEntity user = new UserEntity();
		
		user.setUsername("Test");
		user.setPassword("Test123");
		
		userRepository.save(user);
		
		user = userRepository
				.findByUsername("Test")
				.get();
		
		PostEntity post = new PostEntity();
		
		post.setTitle("Test comment title");
		post.setText("Test comment text");
		post.setUser(user);
		
		postRepository.save(post);
		
		
		List<PostEntity> foundPosts = postRepository.findByUserId(user.getId());
		
		assertThat(foundPosts.isEmpty()).isFalse();
	}
}
