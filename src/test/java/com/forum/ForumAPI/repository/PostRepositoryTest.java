package com.forum.ForumAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
	
	private static final String USERNAME = "Test";
	private static final String USER_PASSWORD = "Test123";
	private static final String POST_TITLE = "Test post title";
	private static final String POST_TEXT = "Test post text";
	
	@Test
	void whenFindByUserId_thenFindPost() {
		
		UserEntity user = new UserEntity();
		
		user.setUsername(USERNAME);
		user.setPassword(USER_PASSWORD);
		
		userRepository.save(user);
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setUser(user);
		
		postRepository.save(post);
		
		List<PostEntity> foundPosts = postRepository.findByUserId(user.getId());
		
		assertThat(foundPosts).isNotEmpty();
	}
	
	@Test
	void whenFindByIdAndIsPublicFalse_thenFindPost() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		
		postRepository.save(post);
		
		PostEntity foundPost = postRepository
								.findByIdAndIsPublic(post.getId(), false)
								.orElse(null);
		assertAll("foundPost",
			() -> assertThat(foundPost).isNotNull(),
			() -> assertThat(foundPost.getId()).isEqualTo(post.getId())
		);
	}
	
	@Test
	void whenFindByIdAndIsPublicTrue_thenFindPost() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setPublic(true);
		
		postRepository.save(post);
		
		PostEntity foundPost = postRepository
								.findByIdAndIsPublic(post.getId(), true)
								.orElse(null);
		
		assertAll("foundPost",
			() -> assertThat(foundPost).isNotNull(),
			() -> assertThat(foundPost.getId()).isEqualTo(post.getId())
		);
	}
	
	@Test
	void whenFindByIdAndIsPublicFalse_thenNotFindPost() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		
		postRepository.save(post);
		
		PostEntity foundPost = postRepository
								.findByIdAndIsPublic(post.getId(), true)
								.orElse(null);
		
		assertThat(foundPost).isNull();
	}
	
	@Test
	void whenFindByUserIdAndIsPublicTrue_thenFindPost() {
		
		UserEntity user = new UserEntity();
		
		user.setUsername(USERNAME+"1");
		user.setPassword(USER_PASSWORD);
		
		userRepository.save(user);
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setPublic(true);
		post.setUser(user);
		
		postRepository.save(post);
		
		List<PostEntity> foundPosts = postRepository.findByUserIdAndIsPublic(user.getId(), true);

		assertThat(foundPosts).isNotEmpty();
	}
	
	@Test
	void whenFindByUserIdAndIsPublicFalse_thenFindPost() {
		
		UserEntity user = new UserEntity();
		
		user.setUsername(USERNAME+"2");
		user.setPassword(USER_PASSWORD);
		
		userRepository.save(user);
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setUser(user);
		
		postRepository.save(post);
		
		List<PostEntity> foundPosts = postRepository.findByUserIdAndIsPublic(user.getId(), false);

		assertThat(foundPosts).isNotEmpty();
	}
	
	@Test
	void whenFindByUserIdAndIsPublicTrue_thenNotFindPost() {
		
		UserEntity user = new UserEntity();
		
		user.setUsername(USERNAME+"3");
		user.setPassword(USER_PASSWORD);
		
		userRepository.save(user);
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setUser(user);
		
		postRepository.save(post);
		
		List<PostEntity> foundPosts = postRepository.findByUserIdAndIsPublic(user.getId(), true);

		assertThat(foundPosts).isEmpty();
	}
	
	@Test
	void whenExistsByIdAndIsPublicTrue_thenTrue() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		post.setPublic(true);
		
		postRepository.save(post);
		
		boolean isFound = postRepository.existsByIdAndIsPublic(post.getId(), true);
		
		assertThat(isFound).isTrue();
	}
	
	@Test
	void whenExistsByIdAndIsPublicFalse_thenTrue() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		
		postRepository.save(post);
		
		boolean isFound = postRepository.existsByIdAndIsPublic(post.getId(), false);
		
		assertThat(isFound).isTrue();
	}
	
	@Test
	void whenExistsByIdAndIsPublicTrue_thenFalse() {
		
		PostEntity post = new PostEntity();
		
		post.setTitle(POST_TITLE);
		post.setText(POST_TEXT);
		
		postRepository.save(post);
		
		boolean isFound = postRepository.existsByIdAndIsPublic(post.getId(), true);
		
		assertThat(isFound).isFalse();
	}
}
