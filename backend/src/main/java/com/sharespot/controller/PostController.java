package com.sharespot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.Post;
import com.sharespot.repo.PostRepository;

@RestController
@RequestMapping("/main")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPost(){
		List<Post> posts = postRepository.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postNo}")
	public ResponseEntity<Optional<Post>> getPost(@PathVariable int postNo){
		Optional<Post> post = postRepository.findById(postNo);
		return new ResponseEntity<Optional<Post>>(post, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> createPost(@RequestBody Post post){
		Post postEntity = Post.builder()
			.userId(post.getUserId())
			.content(post.getContent())
			.postLat(post.getPostLat())
			.postLng(post.getPostLng())
			.image(post.getImage())
			.postGpsName(post.getPostGpsName())
			.classBig(post.getClassBig())
			.classSmall(post.getClassSmall())
			.classWho(post.getClassWho())
			.classWhere(post.getClassWhere())
			.uploadTime(post.getUploadTime())
			.build();
		postRepository.save(postEntity);
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
		
	}
	
}
