package com.forum.ForumAPI.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String username;
	
	@Column
	@JsonIgnore
	private String password;
	
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, 
											 CascadeType.DETACH, CascadeType.REFRESH}) 
	private List<PostEntity> posts;
	  
	public void addPost(PostEntity post) {
	  
		if (posts == null) posts = new ArrayList<>();
	  
		posts.add(post);
	  
		post.setUser(this); 
	}
	 
}
