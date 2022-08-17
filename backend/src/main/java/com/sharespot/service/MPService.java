package com.sharespot.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sharespot.repo.MPRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MPService {

	private final MPRepository mpRepository;
	
	@Transactional
	public void deleteJoin(int userId, int mid) {
		mpRepository.deleteByUserIdAndMeetingId(userId, mid);
	}
}
