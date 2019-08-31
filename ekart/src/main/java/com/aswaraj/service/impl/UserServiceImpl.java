package com.aswaraj.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aswaraj.model.User;
import com.aswaraj.repository.UserRepository;
import com.aswaraj.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUserUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setUserPassword(user.getUserPassword()); 
		user.setUserActive(1);
		return userRepository.saveAndFlush(user);
	}

}
