package com.sharespot.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

//import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Mail;
import com.sharespot.entity.User;
import com.sharespot.repo.UserRepository;
import com.sharespot.security.EncryptionUtils;

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
		User temp = user;
		temp.setPassword(EncryptionUtils.encryptSHA256(user.getPassword()));
		User saveduser = userRepository.save(temp);
		return saveduser;
//		}else {
//			
//		}
	}

	public int modify(String email, User value) {
		Optional<User> mUser = userRepository.findByEmail(email);
		if (mUser.isPresent()) {
			User user = mUser.get();
			if (StringUtils.isNotBlank(value.getProfileImage())) {
				user.setProfileImage(value.getProfileImage());
			}
			if (StringUtils.isNotBlank(value.getNickname())) {
				user.setNickname(value.getNickname());
			}
			if (StringUtils.isNotBlank(value.getIntroduce())) {
				user.setIntroduce(value.getIntroduce());
			}
			if (StringUtils.isNotBlank(value.getPhone())) {
				user.setPhone(value.getPhone());
			}
			user.setAL(value.getAL());
			user.setBD(value.getBD());
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

	public User login(String email, String password) {
		return userRepository.findByEmailAndPassword(email, EncryptionUtils.encryptSHA256(password));
	}

	public List<User> getUsersBySearch(String searchWord, String searchWord2) {
		return userRepository.findByNicknameContainingOrIntroduceContaining(searchWord, searchWord2);
	}

	public Mail sendEmailService(String email, String name) {

		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			User temp = user.get();

			String str = getTempPassword(); // 비밀번호 랜덤 재설정
			Mail mail = new Mail();

			mail.setAddress(email);
			mail.setTitle(name + "님의 비밀번호 재발급 안내 이메일 입니다.");
			mail.setContent("안녕하세요. ShareSpot 임시 비밀번호 안내 관련 이메일 입니다. 재설정된 비밀번호는" + "[ " + str + " ] 입니다.");

			temp.setPassword(EncryptionUtils.encryptSHA256(str));
			userRepository.save(temp);

			return mail;
		}
		return null;

	}

	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String str = "";

		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}

}
