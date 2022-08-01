package com.sharespot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.service.TestService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/test")
@RestController
public class TestController {
	private final TestService testService;
	
	
}
