package com.forum.ForumAPI.service;

import java.security.AccessControlException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.Comment;
import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
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
	public Comment findById(long commentId){
		return commentRepository
				.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException(COMMENT_NOT_FOUND_EXCEPTION_MESSAGE));
	}
	
	@Override
	public void save(Comment comment, long postId) throws ResourceNotFoundException {
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);
		
		UserEntity user = authenticatedUserDetails.getUser();
		
		comment.setPost(post);
		comment.setUser(user);
		
		commentRepository.save(comment);
	}

	@Override
	public void update(Comment comment)  {

		Comment newComment = commentRepository
								.findById(comment.getId())
								.orElseThrow(()-> new ResourceNotFoundException(COMMENT_NOT_FOUND_EXCEPTION_MESSAGE));
		
		newComment.setText(comment.getText());
		newComment.setDate(LocalDateTime.now());
		newComment.setEdited();
		
		commentRepository.save(newComment);
	}
	
	@Override
	public void delete(long commentId){

		Comment comment = findById(commentId);
		
		long userId = comment
						.getUser()
						.getId();
		
		long currentUserId = authenticatedUserDetails.getUserId();
		
		if (currentUserId == userId) {
			commentRepository.delete(comment);
		}
		else {
			throw new AccessControlException("You have no permission to delete this comment");
		}
	}
	
	@Override
	public List<Comment> findByPostId(long postId){

		postService.findByIdWithPublicAccess(postId);
		
		return commentRepository.findByPostId(postId);
	}
}
