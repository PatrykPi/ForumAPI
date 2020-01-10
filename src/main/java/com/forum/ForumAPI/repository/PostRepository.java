package com.forum.ForumAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.ForumAPI.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
	
	List<PostEntity> findByUserId(long userId);
	
	Optional<PostEntity> findByIdAndIsPublic(long id, boolean isPublic);
	
	List<PostEntity> findByUserIdAndIsPublic(long userId, boolean isPublic);
	
	boolean existsByIdAndIsPublic(long id, boolean isPublic);
}
