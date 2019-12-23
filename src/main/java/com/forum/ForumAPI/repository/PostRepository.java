package com.forum.ForumAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.ForumAPI.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
	
	List<PostEntity> findByUserId(long userId);
}