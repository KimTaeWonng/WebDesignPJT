package com.sharespot.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import io.swagger.annotations.ApiOperation;
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
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;
	private final UserRepository userRepository;

	@PostMapping("/signup")   //회원 등록
	@ApiOperation(value = "회원 등록")
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) throws ParseException {

		Map<String, Object> result = new HashMap<>();

		User userEntity = User.builder().user_id(user.getUser_id()).email(user.getEmail()).password(user.getPassword())
				.phone(user.getPhone()).gender(user.getGender()).birth(user.getBirth()).name(user.getName())
				.nickname(user.getNickname()).introduce(user.getIntroduce()).profileImage(user.getProfileImage())
				.BD(user.getBD()) // isBadge
				.AL(user.getAL()) // isalarm
				.GD(user.getGD()) // isgender
				.BR(user.getBR()) // isBirth
				.PB(user.getPB()) // isPublic
				.userGrade(user.getUserGrade()).build();

		try {
			User savedUser = userService.createUser(userEntity);
			String token = jwtService.create("user_id", user.getUser_id(), "Authorization");
			result.put("Authorization", token);
			result.put("message", SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@GetMapping("/idcheck/{email}")
	@ApiOperation(value = "아이디(이메일) 중복 체크")
	public ResponseEntity<Boolean> checkid(@PathVariable String email) {

		boolean check = userService.idCheck(email);

		return ResponseEntity.ok(userService.idCheck(email));

	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "회원 탈퇴")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id , HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println("통신 들어옴");
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId() == id) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
			System.out.println(id);
				try {
					//	로그인 사용자 정보.
					userService.deleteUser(id);
					result.put("message", SUCCESS);
					result.put("Authorization", null);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("회원 정보 삭제 실패: {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);

	}

	@GetMapping("/logout")
	@ApiOperation(value = "회원 로그아웃")
	public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) throws Exception {

		logger.debug("logout - 호출");
		Map<String,Object> result = new HashMap<>();
		
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			result.put("Authorization", null);
			result.put("message", SUCCESS);
		} else {
			result.put("message", FAIL);
		}
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}
	
	@GetMapping("/valid")
	@ApiOperation(value = "토큰 유효성 검사")
	public ResponseEntity<Map<String,Object>> tokenValidation(HttpServletRequest request) {
		logger.info("tokenValidation");
		Map<String, Object> result = new HashMap<>();
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			result.put("message", SUCCESS);
		} else {
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}

	
	
	@GetMapping("/info/{userid}")
	@ApiOperation(value = "유저 본인의 정보를 불러온다", notes = "보려는 정보가 본인의 것이면 정보를 반환한다")
	public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable ("userid") int userid ,@ApiParam(value = "인증할 회원의 아이디.", required = true) 
			HttpServletRequest request) {
		//logger.debug("userid : {} ", userid);
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(userid);
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId() == userid) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					//	로그인 사용자 정보.
					Optional<User> userInfo = userService.getUser(userid);
					result.put("userInfo", userInfo);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("정보조회 실패 : {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@GetMapping("/info/{userId}/otherUser")
	@ApiOperation(value = "다른 유저의 마이페이지 조회", notes = "다른 유저의 마이페이지 값을 반환한다")
	public ResponseEntity<Object[]> getOtherUserInfo(@PathVariable ("userId") int userId){
		Object[] users = userRepository.findByUserId(userId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("{userid}")
	@ApiOperation(value = "회원 수정", notes = "입력한 정보를 바탕으로 회원정보를 수정한다")
	public ResponseEntity<Map<String, Object>> modifyUser(@RequestBody User user, HttpServletRequest request)  throws Exception {

		User userEntity = User.builder().user_id(user.getUser_id()).email(user.getEmail()).password(user.getPassword())
				.phone(user.getPhone()).gender(user.getGender()).birth(user.getBirth()).name(user.getName())
				.nickname(user.getNickname()).introduce(user.getIntroduce()).profileImage(user.getProfileImage())
				.BD(user.getBD()) // isBadge
				.AL(user.getAL()) // isalarm
				.GD(user.getGD()) // isgender
				.BR(user.getBR()) // isBirth
				.PB(user.getPB()) // isPublic
				.userGrade(user.getUserGrade()).build();

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId() != userEntity.getUser_id()) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					//	로그인 사용자 정보.
					userService.modify(user.getEmail(), userEntity);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("회원 정보 업데이트 실패: {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);


	}

	@PostMapping("/login")
	@ApiOperation(value = "회원 로그인", notes = "입력한 정보를 바탕으로 로그인")
	public ResponseEntity<Map<String, Object>> login (
			@RequestBody @ApiParam(value = "로그인은 이메일과 비밀번호를 이용.", required = true) User user)  throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user.getEmail(), user.getPassword());
			logger.debug("로그인 토큰정보 : {}", user.getEmail());
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
			System.out.println("==== catch 입장=====");
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("info/user/{userId}")
	@ApiOperation(value = "유저 닉네임 불러오기", notes = "유저 아디 주면 닉넴 불러옴")
	public ResponseEntity<String> takeNick(@PathVariable int userId){
		
		User user = userService.getUser(userId).get();
		
		String result = user.getNickname();
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
		
	}

}
