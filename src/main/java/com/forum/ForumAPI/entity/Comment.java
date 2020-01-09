package com.forum.ForumAPI.entity;

import java.time.LocalDateTime;

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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	@Column
	@NotBlank(message = "text is required")
	private String text;
	
	@Column
	@Setter(AccessLevel.NONE)
	private boolean isEdited;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			  									  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "post_id")
	@JsonIgnore	
	private PostEntity post;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			  									  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;
	
	private LocalDateTime date = LocalDateTime.now();
	
	public void setEdited() {
		isEdited = true;
	}
	
}
