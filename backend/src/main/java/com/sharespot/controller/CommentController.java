package com.sharespot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.Comment;
import com.sharespot.repo.CommentRepository;

@RestController
@RequestMapping("/main/posts")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@PostMapping("/{postNo}/comments")
	public ResponseEntity<Integer> createComment(@RequestBody Comment comment, @PathVariable int postNo){
		Comment commentEntity = Comment.builder()
				.postId(comment.getPostId())
				.userId(comment.getUserId())
				.comment(comment.getComment())
				.build();
		
		int result = 1;
		try {
			commentRepository.save(commentEntity);
		} catch (Exception e) {
			result = 0;
		}
				
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PutMapping("/{postNo}/comments/{commentNo}")
	public ResponseEntity<Integer> updateComment(@RequestBody Comment comment, @PathVariable int postNo,int commentNo){
		Optional<Comment> option = commentRepository.findById(commentNo);
		int result = 0;
		if(option.isPresent()) {
			Comment c = option.get();
			
			c.setComment(comment.getComment());
			commentRepository.save(c);
			
			result = 1;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{postNo}/comments/{commentNo}")
	public ResponseEntity<Integer> deleteComment(@PathVariable int postNo, int commentNo){
		int result = 0;
		if(commentRepository.findById(commentNo).isPresent()) {
			commentRepository.deleteById(commentNo);
			result = 1;
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
