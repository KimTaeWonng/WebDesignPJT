package com.sharespot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Scrap;
import com.sharespot.repo.ScrapRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
	
	@Autowired
	private ScrapRepository scrapRepository;
	
	public List<Scrap> scrapList(int userId){
		return scrapRepository.findByUserId(userId);	
	}
	
	@Transactional
	public Scrap createScrap(Scrap scrap) {
		Scrap savedScrap = scrapRepository.save(scrap);
		
		return savedScrap;
	}
	
	@Transactional
	public void deleteScrap(int postId) {
		scrapRepository.deleteByPostId(postId);
	}

}
