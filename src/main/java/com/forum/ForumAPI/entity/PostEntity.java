package com.forum.ForumAPI.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@NotBlank(message = "Title is required")
	private String title;

	@Column
	@NotBlank(message = "Text is required")
	private String text;

	@Column
	private LocalDateTime date = LocalDateTime.now();
	
	@Column
	private boolean isPublic;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
						  CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@JsonIgnore 
	private UserEntity user;
}
