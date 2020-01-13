package com.forum.ForumAPI.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_ratings")
public class PostRatingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;

	@Column
	private boolean isLiked;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			  									  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "post_id")
	@JsonIgnore
	@Setter
	private PostEntity post;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			  									  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore
	@Setter
	private UserEntity user;

	public PostRatingEntity() {
	}
	
	public void setLiked() {
		this.isLiked = true;
	}
	
	public void setDisliked() {
		this.isLiked = false;
	}

	public boolean isLiked() {
		return isLiked;
	}
}
