package com.forum.ForumAPI.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.CommentEntity;
import com.forum.ForumAPI.entity.PostEntity;
import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.NoPermissionException;
import com.forum.ForumAPI.exception.ResourceNotFoundException;
import com.forum.ForumAPI.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AuthenticatedUserDetails authenticatedUserDetails;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public CommentEntity findById(long commentId){
		return commentRepository
				.findById(commentId)
				.orElseThrow(()-> new ResourceNotFoundException("Comment with id " + commentId + " not found"));
	}
	
	@Override
	public void save(CommentEntity comment, long postId){
		
		PostEntity post = postService.findByIdWithPublicAccess(postId);
		
		UserEntity user = authenticatedUserDetails.getUser();
		
		comment.setPost(post);
		comment.setUser(user);
		
		commentRepository.save(comment);
	}

	@Override
	public void update(CommentEntity comment)  {

		CommentEntity newComment = findById(comment.getId());

		if (checkUserPermission(newComment)) throw new NoPermissionException("You have no permission to update this comment");
		
		newComment.setText(comment.getText());
		newComment.setDate(LocalDateTime.now());
		newComment.setEdited();
		
		commentRepository.save(newComment);
	}
	
	@Override
	public void delete(long commentId){

		CommentEntity comment = findById(commentId);
		
		if (checkUserPermission(comment)) throw new NoPermissionException("You have no permission to delete this comment");
		
		commentRepository.delete(comment);
	}
	
	@Override
	public List<CommentEntity> findByPostId(long postId){

		if(!postService.existsByIdWithPublicAccess(postId)) throw new ResourceNotFoundException("Post with id " + postId+ " and public access not found");
		
		return commentRepository.findByPostId(postId);
	}
	
	private boolean checkUserPermission(CommentEntity comment) {
		
		long userId = comment
						.getUser()
						.getId();

		long currentUserId = authenticatedUserDetails.getUserId();
		
		return currentUserId != userId;
	}
}
