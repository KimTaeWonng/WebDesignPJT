package com.sharespot.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@GetMapping("/posts/user/{userId}")
	@ApiOperation(value = "유저의 게시글리스트 조회", notes = "해당 userId의 게시글 목록을 반환한다.")
	public ResponseEntity<List<Object[]>> getUserPost(@PathVariable int userId){
		List<Object[]> post = postRepository.findByUserId(userId);
		return new ResponseEntity<>(post, HttpStatus.OK);

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

	@GetMapping("/search/posts/new")
	@ApiOperation(value = "게시글 페이지네이션 조회", notes = "<b>게시글</b>page와 size로 조회 한다.")
	public Page<Post> getAllPosts(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return postRepository.findAll(pageRequest);
	}


}
