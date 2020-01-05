package com.forum.ForumAPI.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forum.ForumAPI.entity.UserEntity;
import com.forum.ForumAPI.exception.UserAlreadyExistsException;
import com.forum.ForumAPI.model.CustomUserDetails;
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
		
		UserEntity user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new CustomUserDetails(user.getUsername(), user.getPassword(), new ArrayList<>(), user.getId());
	}
	
	@Override
	public UserEntity save(UserDTO user) throws UserAlreadyExistsException {
		
		boolean isFound = userRepository
				.findByUsername(user.getUsername())
				.isPresent();
		
		if (isFound) throw new UserAlreadyExistsException("User already exists");
		
		UserEntity newUser = new UserEntity();
		
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		return userRepository.save(newUser);
	}
	

	@Override
	public void update(UserEntity user) {
		userRepository.save(user);
	}

	@Override
	public UserEntity findByUsername(String username) {		
		return userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

	@Override
	public UserEntity findById(long userId) {
		return userRepository
				.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
	}

}
