package com.sharespot.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.Alarm;
import com.sharespot.entity.User;
import com.sharespot.service.AlarmService;
import com.sharespot.service.JwtServiceImpl;
import com.sharespot.service.UserService;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/setting")
public class AlarmController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtServiceImpl jwtService;

	@Transactional
	@PostMapping("/create/{userid}") // 회원 등록
	public ResponseEntity<Map<String, Object>> signUp(@PathVariable int userid) throws ParseException {

		Map<String, Object> result = new HashMap<>();

		if (userService.getUser(userid) != null) {
			Alarm alarmEntity = Alarm.builder().user_id(userid).is_heart(1).is_comment(1).is_group(1)
					.is_follow(1).is_feed(1).is_chat(1).build(); // 회원을 만들때 모든 알람설정을 켜놓은 상태로 생성

			Alarm savedAlarm = alarmService.createAlarm(alarmEntity);

		}

		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@GetMapping("/info/{userid}") // 이전까지 설정해놨던 알람정보 불러오기
	public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable int userid,
			@ApiParam(value = "인증할 회원의 아이디.", required = true) HttpServletRequest request) {
		// logger.debug("userid : {} ", userid);
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if (!jwtService.getUserId().equals(userid)) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					// 로그인 사용자 정보.
					Optional<Alarm> alarmInfo = alarmService.getAlarm(userid);
					result.put("userInfo", alarmInfo);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("정보조회 실패 : {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			} else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}

		} else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@PutMapping("/notice/{userid}")
	public ResponseEntity<Map<String, Object>> modifyUser(@RequestBody Alarm alarm, HttpServletRequest request)
			throws Exception {

		Alarm alarmEntity = Alarm.builder().user_id(alarm.getUser_id()).is_heart(alarm.getIs_heart())
				.is_comment(alarm.getIs_comment()).is_group(alarm.getIs_group()).is_follow(alarm.getIs_follow())
				.is_feed(alarm.getIs_feed()).is_chat(alarm.getIs_chat()).build();

		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if (jwtService.getUserId().equals(alarmEntity.getUser_id())) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					// 로그인 사용자 정보.
					alarmService.modify(alarm.getUser_id(), alarm);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("회원 정보 업데이트 실패: {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			} else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}

		} else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);

	}
}
