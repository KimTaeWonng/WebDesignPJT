package com.sharespot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Follow;
import com.sharespot.repo.FollowRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowService {
	
	@Autowired
	private FollowRepository followRepository;
	
	public List<Follow> followerList(int userId){
		return followRepository.findByUserId(userId);
	}

}
