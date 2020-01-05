package com.forum.ForumAPI.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.forum.ForumAPI.entity.PostRatingEntity;

@Repository
public interface PostRatingRepository extends CrudRepository<PostRatingEntity, Long> {

	Optional<PostRatingEntity> findByUserIdAndPostId(long userId, long postId);
}
