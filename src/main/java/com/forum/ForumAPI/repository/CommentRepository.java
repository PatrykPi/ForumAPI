package com.forum.ForumAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.ForumAPI.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
