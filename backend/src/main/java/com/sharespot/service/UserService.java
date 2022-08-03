package com.sharespot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.User;
import com.sharespot.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {

//		if (userRepository.findById(user.getId()) == null) {
			User saveduser = userRepository.save(user);
			return saveduser;
//		}else {
//			
//		}
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(Integer id) {
		return userRepository.findById(id);
	}

	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
		
	public boolean idCheck(String email) {
		return userRepository.existsByEmail(email);
	}
	
	
}
