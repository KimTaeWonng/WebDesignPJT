package com.sharespot.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.User;
import com.sharespot.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	
	private final UserRepository userRepository;

	@Transactional
	public User createUser(User user) {

//		if (userRepository.findById(user.getId()) == null) {
			User saveduser = userRepository.save(user);
			return saveduser;
//		}else {
//			
//		}
	}
	
	public int modify(String email, User value) {
		Optional<User> mUser = userRepository.findByemail(email);
		if(mUser.isPresent()) {
			User user = mUser.get();
			if(StringUtils.isNotBlank(value.getProfile_image())) {
				user.setProfile_image(value.getProfile_image());
			}
			if(StringUtils.isNotBlank(value.getNickname())) {
				user.setNickname(value.getNickname());
			}
			if(StringUtils.isNotBlank(value.getIntroduce())) {
				user.setIntroduce(value.getIntroduce());
			}
			if(StringUtils.isNotBlank(value.getPhone())) {
				user.setPhone(value.getPhone());
			}
			user.setPB(value.getPB());
			user.setBR(value.getBR());
			user.setGD(value.getGD());
			user.setGender(value.getGender());
			userRepository.save(user);
			
			return 1;
		}
		return 0;
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
