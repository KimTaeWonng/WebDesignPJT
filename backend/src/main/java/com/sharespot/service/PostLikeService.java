package com.sharespot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.PostLike;
import com.sharespot.repo.PostLikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeService {
	
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Transactional
	public PostLike createLike(PostLike postLike) {
		PostLike savedLike = postLikeRepository.save(postLike);
		return savedLike;
	}
	
	@Transactional
	public void deleteLike(int postId,int userId) {
		postLikeRepository.deleteByPostIdAndUserId(postId, userId);
	}

}
