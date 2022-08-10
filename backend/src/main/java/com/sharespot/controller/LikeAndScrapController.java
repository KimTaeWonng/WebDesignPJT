package com.sharespot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.PostLike;
import com.sharespot.entity.Scrap;
import com.sharespot.repo.PostLikeRepository;
import com.sharespot.repo.ScrapRepository;
import com.sharespot.service.PostLikeService;
import com.sharespot.service.PostService;
import com.sharespot.service.ScrapService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/LikeScrap")
public class LikeAndScrapController {
	
	@Autowired
	private PostLikeService postLikeService;
	@Autowired
	private PostLikeRepository postLikeRepository;
	
	@Autowired
	private ScrapService scrapService;
	@Autowired
	private ScrapRepository scrapRepository;
	
	
	@GetMapping("/listL/{userId}")
	@ApiOperation(value = "좋아요 리스트", notes = "유저의 모든 좋아요 리스트")
	public ResponseEntity<List<PostLike>> getLikeAll(@PathVariable int userId){
		
		List<PostLike> likeAll = postLikeRepository.findByUserId(userId);
		
		return new ResponseEntity<List<PostLike>>(likeAll,HttpStatus.OK);
	}
	
	@GetMapping("/listS/{userId}")
	@ApiOperation(value = "스크랩 리스트", notes = "유저의 모든 스크랩 리스트")
	public ResponseEntity<List<Scrap>> getScrapAll(@PathVariable int userId){
		
		List<Scrap> scrapAll = scrapRepository.findByUserId(userId);
		
		return new ResponseEntity<List<Scrap>>(scrapAll,HttpStatus.OK);
	}

}
