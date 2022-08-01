package com.sharespot.service;

import org.springframework.stereotype.Service;

import com.sharespot.entity.TestEntity;
import com.sharespot.repo.TestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	private final TestRepository testRepository;
	
	public TestEntity createTest(TestEntity testEntity) {
		TestEntity savedTest = testRepository.save(testEntity);
		return savedTest;
	}
	
}
