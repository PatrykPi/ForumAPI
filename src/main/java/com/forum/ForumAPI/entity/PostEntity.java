package com.forum.ForumAPI.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@NotBlank(message = "title is required")
	private String title;

	@Column
	@NotBlank(message = "text is required")
	private String text;

	@Column
	private LocalDateTime date = LocalDateTime.now();
	
	@Column
	private boolean isPublic;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						  						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore 
	private UserEntity user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
	@JsonIgnore	
	private List<PostRatingEntity> postRatings;
	
	public void addPostRating(PostRatingEntity postRating) {
		  
		if (postRatings == null) postRatings = new ArrayList<>();
	  
		postRatings.add(postRating);
	  
		postRating.setPost(this);
	}
}
