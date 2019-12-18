package com.forum.ForumAPI.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.model.UserDTO;
import com.forum.ForumAPI.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		}
	}
	
	@Override
	public UserEntity save(UserDTO user) {
		
		UserEntity newUser = new UserEntity();
		
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		return userRepository.save(newUser);
	}

}
