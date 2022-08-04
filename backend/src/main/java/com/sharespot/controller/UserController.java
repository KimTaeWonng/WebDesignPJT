package com.sharespot.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.User;
import com.sharespot.repo.UserRepository;
import com.sharespot.service.JwtService;
import com.sharespot.service.JwtServiceImpl;
import com.sharespot.service.UserService;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) throws ParseException {

		Map<String, Object> result = new HashMap<>();

		User userEntity = User.builder().user_id(user.getUser_id()).email(user.getEmail()).password(user.getPassword())
				.phone(user.getPhone()).gender(user.getGender()).birth(user.getBirth()).name(user.getName())
				.nickname(user.getNickname()).introduce(user.getIntroduce()).profile_image(user.getProfile_image())
				.BD(user.getBD()) // isBadge
				.alarm(user.getAlarm()) // isalarm
				.GD(user.getGD()) // isgender
				.BR(user.getBR()) // isBirth
				.PB(user.getPB()) // isPublic
				.user_grade(user.getUser_grade()).build();

		try {
			User savedUser = userService.createUser(userEntity);
			String token = jwtService.create("email", user.getEmail(), "Authorization");
			result.put("Authorization", token);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@GetMapping("/idcheck/{email}")
	public ResponseEntity<Boolean> checkid(@PathVariable String email) {

		boolean check = userService.idCheck(email);

		return ResponseEntity.ok(userService.idCheck(email));

	}

	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);

		return new ResponseEntity<Integer>(id, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<Boolean> logout() {

		boolean logout = false;

		// jwt 사용

		//
		return new ResponseEntity<Boolean>(logout, HttpStatus.OK);
	}

	@PutMapping("{userid}")
	public ResponseEntity<Integer> modifyUser(@RequestBody User user) {

		User userEntity = User.builder().user_id(user.getUser_id()).email(user.getEmail()).password(user.getPassword())
				.phone(user.getPhone()).gender(user.getGender()).birth(user.getBirth()).name(user.getName())
				.nickname(user.getNickname()).introduce(user.getIntroduce()).profile_image(user.getProfile_image())
				.BD(user.getBD()) // isBadge
				.alarm(user.getAlarm()) // isalarm
				.GD(user.getGD()) // isgender
				.BR(user.getBR()) // isBirth
				.PB(user.getPB()) // isPublic
				.user_grade(user.getUser_grade()).build();

		Integer result = userService.modify(user.getEmail(), userEntity);

		return new ResponseEntity<Integer>(result, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인은 이메일과 비밀번호를 이용.", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user.getEmail(), user.getPassword());
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUser_id(), "Authorization");// key, data,
																									// subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("Authorization", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
