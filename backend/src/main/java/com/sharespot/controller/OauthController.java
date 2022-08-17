package com.sharespot.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.service.OauthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
@Slf4j
public class OauthController {
	
	@Autowired
	private OauthService oauthService;
	
	@ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String accessToken = oauthService.getKakaoAccessToken(code);
        
        System.out.println(accessToken);
    }
	
	@RequestMapping(value = "/login")
	public ResponseEntity<HashMap<String, String>> login(@RequestParam("token") String token) throws IOException {
		
		HashMap<String, String> result = new HashMap<String, String>();
	   result = oauthService.createKakaoUser(token);
	   System.out.println(result);
	   
	   return new ResponseEntity<HashMap<String, String>>(result,HttpStatus.OK);
	}

	
	
	

}
