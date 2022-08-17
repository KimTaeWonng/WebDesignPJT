package com.sharespot.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sharespot.repo.GroupRepository;
import com.sharespot.repo.GroupUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GUService {
	
	private final GroupUserRepository guRepository;
	
	@Transactional
	public void deleteJoin(int userId, int gid) {
		guRepository.deleteByUserIdAndGroupId(userId, gid);
	}

}
