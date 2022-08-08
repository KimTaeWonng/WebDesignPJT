package com.sharespot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.Post;
import com.sharespot.repo.PostRepository;
import com.sharespot.service.JwtServiceImpl;
import com.sharespot.service.PostService;
import com.sharespot.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/search")
public class CurationController {
	
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/posts/category/{big}/{small}/{who}/{where}")
	public ResponseEntity<List<Post>> curationList(@PathVariable String big, @PathVariable String[] small, @PathVariable String[] who, @PathVariable String[] where){
		List<Post> curationResult = postService.CurationList(big, small, who, where);
		
		return new ResponseEntity<List<Post>>(curationResult,HttpStatus.OK);
	}
}
