package com.forum.ForumAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.ForumAPI.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
	
	List<CommentEntity> findByPostId(long postId);
}
