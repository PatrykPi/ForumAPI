package com.forum.ForumAPI.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.Comment;
import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.CommentNotFoundException;
import com.forum.ForumAPI.exception.PostNotFoundException;
import com.forum.ForumAPI.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	private static final String COMMENT_NOT_FOUND_EXCEPTION_MESSAGE = "Comment not found";
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AuthenticatedUserDetails authenticatedUserDetails;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public void save(Comment comment, long postId) throws PostNotFoundException {
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);
		
		UserEntity user = authenticatedUserDetails.getUser();
		
		comment.setPost(post);
		comment.setUser(user);
		
		commentRepository.save(comment);
	}

	@Override
	public void update(Comment comment) throws CommentNotFoundException {

		Comment newComment = commentRepository
								.findById(comment.getId())
								.orElseThrow(()-> new CommentNotFoundException(COMMENT_NOT_FOUND_EXCEPTION_MESSAGE));
		
		newComment.setText(comment.getText());
		newComment.setDate(LocalDateTime.now());
		newComment.setEdited();
		
		commentRepository.save(newComment);
	}

}
