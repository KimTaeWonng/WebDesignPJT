package com.sharespot.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@ApiOperation(value = "게시글목록", notes = "<b>게시글 전체 목록</b>을 반환한다.")
	public ResponseEntity<List<Post>> getAllPost(){
		List<Post> posts = postRepository.findAll();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postNo}")
	@ApiOperation(value = "게시글 상세조회", notes = "<b>해당 게시글의 댓글</b>을 반환한다.")
	public ResponseEntity<Optional<Post>> getPost(@PathVariable int postNo){
		Optional<Post> post = postRepository.findById(postNo);
		return new ResponseEntity<Optional<Post>>(post, HttpStatus.OK);
	}
	
	@PostMapping("/posts")
	@ApiOperation(value = "게시글 작성", notes = "<b>게시글을 작성</b>한다.")
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
		int result = 1;
		try {
			postRepository.save(postEntity);
		} catch (Exception e) {
			result = 0;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	@PutMapping("/posts/{postNo}")
	@ApiOperation(value = "게시글 수정", notes = "<b>해당 게시글을 수정</b>한다.")
	public ResponseEntity<Integer> updatePost(@RequestBody Post post, @PathVariable int postNo){
		Optional<Post> option = postRepository.findById(postNo);
		int result = 0;
		if(option.isPresent()) { 
			
			Post p = option.get();

			p.setContent(post.getContent());
			p.setPostLat(post.getPostLat());
			p.setPostLng(post.getPostLng());
			p.setImage(post.getImage());
			p.setPostGpsName(post.getPostGpsName());
			p.setClassBig(post.getClassBig());
			p.setClassSmall(post.getClassSmall());
			p.setClassWho(post.getClassWho());
			p.setClassWhere(post.getClassWhere());
			postRepository.save(p);
			
			result = 1;
			}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postNo}")
	@ApiOperation(value = "게시글 삭제", notes = "<b>해당 게시글을 삭제</b>한다.")
	public ResponseEntity<Integer> deletePost(@PathVariable int postNo){
		int result = 0;
		if(postRepository.findById(postNo).isPresent()) {
			postRepository.deleteById(postNo);
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
