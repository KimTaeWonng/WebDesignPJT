package com.sharespot.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.User;
import com.sharespot.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user) throws ParseException {
		User userEntity = User.builder().user_id(user.getUser_id()).email(user.getEmail()).password(user.getPassword())
				.phone(user.getPhone()).gender(user.getGender()).birth(user.getBirth()).name(user.getName())
				.nickname(user.getNickname()).introduce(user.getIntroduce()).profile_image(user.getProfile_image())
				.BD(user.getBD()) // isBadge
				.alarm(user.getAlarm()) // isalarm
				.GD(user.getGD()) // isgender
				.BR(user.getBR()) // isBirth
				.PB(user.getPB()) // isPublic
				.user_grade(user.getUser_grade()).build();

		User savedUser = userService.createUser(userEntity);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
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

//	@PostMapping("/login")
//	public ResponseEntity<T>

}
