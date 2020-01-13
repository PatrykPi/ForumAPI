package com.forum.ForumAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.forum.ForumAPI.entity.CommentEntity;
import com.forum.ForumAPI.entity.PostEntity;

@DataJpaTest
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	void whenFindByPostId_thenFindComments() {
		
		PostEntity post = new PostEntity();
		post.setTitle("text text");
		post.setText("text text");
		postRepository.save(post);
		
		CommentEntity comment = new CommentEntity();
		comment.setText("text text");
		comment.setPost(post);
		commentRepository.save(comment);
		
		List<CommentEntity> foundComments = commentRepository.findByPostId(post.getId());
		
		assertThat(foundComments).isNotEmpty();
	}
	
}
