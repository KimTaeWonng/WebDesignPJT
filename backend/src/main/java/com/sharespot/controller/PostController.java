package com.sharespot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sharespot.entity.Follow;
import com.sharespot.entity.Post;
import com.sharespot.entity.PostLike;
import com.sharespot.entity.Scrap;
import com.sharespot.entity.User;
import com.sharespot.repo.FollowRepository;
import com.sharespot.repo.PostLikeRepository;
import com.sharespot.repo.PostRepository;
import com.sharespot.repo.ScrapRepository;
import com.sharespot.repo.UserRepository;
import com.sharespot.service.PostLikeService;
import com.sharespot.service.ScrapService;

@RestController
@RequestMapping("/main")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private ScrapRepository scrapRepository;
	@Autowired
	private ScrapService scrapService;
	
	@Autowired
	private PostLikeRepository postLikeRepository;	
	@Autowired
	private PostLikeService postLikeService;
	
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
		
		User user = userRepository.findById(post.getUserId()).get();
		
		
		Post postEntity = Post.builder()
			.userId(post.getUserId())
				.nickname(user.getNickname())
				.userImage(user.getProfileImage())
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
				.likeCnt(post.getLikeCnt())
				.commentCnt(post.getCommentCnt())
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
			
			User user = userRepository.findById(post.getUserId()).get();

			p.setContent(post.getContent());
			p.setPostLat(post.getPostLat());
			p.setPostLng(post.getPostLng());
			p.setImage(post.getImage());
			p.setPostGpsName(post.getPostGpsName());
			p.setClassBig(post.getClassBig());
			p.setClassSmall(post.getClassSmall());
			p.setClassWho(post.getClassWho());
			p.setClassWhere(post.getClassWhere());
			p.setLikeCnt(post.getLikeCnt());
			p.setCommentCnt(post.getCommentCnt());
			p.setNickname(user.getNickname());
			p.setUserImage(user.getProfileImage());
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
	
	@GetMapping("/search-user/{searchWord}")
	@ApiOperation(value = "유저 검색 결과", notes = "<b>검색 결과 조건에 맞는 유저를 조회</b>한다.")
	public ResponseEntity<List<User>> searchUser(@PathVariable String searchWord){
		List<User> searchList = userRepository.findByNicknameContainingOrIntroduceContaining(searchWord,searchWord); 
		
		return new ResponseEntity<List<User>>(searchList,HttpStatus.OK);
	}

	@GetMapping("/search/posts/new")
	@ApiOperation(value = "게시글 페이지네이션 조회", notes = "<b>게시글</b>page와 size로 조회 한다.")
	public ResponseEntity<Page<Post>> getAllPosts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("postId").descending());
		return new ResponseEntity<>(postRepository.findAll(pageRequest), HttpStatus.OK);
	}
	
	@GetMapping("/posts/follow/{userId}")
	@ApiOperation(value = "팔로잉 유저 게시글", notes = "팔로잉한 유저가 쓴 게시글들만 조회")
	public ResponseEntity<List<Post>> followList(@PathVariable int userId){
		
		List<Follow> followr_list = followRepository.findByUserId(userId);
		
		List<Post> savedPost = new ArrayList<Post>();
		
		for(Follow f : followr_list) {
			List<Post> post = postRepository.findByUserIdOrderByPostIdDesc(f.getFollowerId());
			
			for(Post p :post) {
				savedPost.add(p);
			}
		}
		
		return new ResponseEntity<List<Post>>(savedPost,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/scrap/{userId}")
	@ApiOperation(value = "스크랩 유저 게시글", notes = "유저가 스크랩한 게시글들만 조회")
	public ResponseEntity<List<Post>> scrapList(@PathVariable int userId){
		
		List<Scrap> scrap_list = scrapRepository.findByUserId(userId);
		
		List<Post> savedPost = new ArrayList<Post>();
		
		for(Scrap s : scrap_list) {
			List<Post> post = postRepository.findByUserIdOrderByPostIdDesc(s.getPostId());
			
			for(Post p :post) {
				savedPost.add(p);
			}
		}
		
		return new ResponseEntity<List<Post>>(savedPost,HttpStatus.OK);
		
	}
	
	@PostMapping("/posts/scrap/{postId}/{userId}")
	@ApiOperation(value = "게시글 스크랩하기", notes = "유저가 스크랩 게시글을 추가")
	public ResponseEntity<Integer> scrapPush(@PathVariable int userId, @PathVariable int postId){
		
		Scrap scrapEntity = Scrap.builder().userId(userId).postId(postId).build();
		
		int result = 1;
		try {
			scrapService.createScrap(scrapEntity);
		} catch (Exception e) {
			result = 0;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/scrap/{postId}")
	@ApiOperation(value = "스크랩 삭제", notes = "스크랩한거 삭제")
	public ResponseEntity<Integer> scrapDelete(@PathVariable int postId, @RequestParam int userId){
		int result = 0;
		if(scrapRepository.findByPostIdAndUserId(postId, userId)!=null) {
			scrapRepository.deleteByPostIdAndUserId(postId, userId);
			result = 1;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PostMapping("/posts/like/{postId}/{userId}")
	@ApiOperation(value = "게시글 좋아요하기", notes = "유저가 게시글을 좋아요")
	public ResponseEntity<Integer> postLike(@PathVariable int userId, @PathVariable int postId, @RequestBody String userNick){
		
		PostLike likeEntity = PostLike.builder().postId(postId).userId(userId).userNick(userNick).build();
		
		int result = 1;
		try {
			postLikeService.createLike(likeEntity);
		} catch (Exception e) {
			result = 0;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/like/{postId}/{userId}")
	@ApiOperation(value = "좋아요 삭제", notes = "좋아요한거 삭제")
	public ResponseEntity<Integer> postLikeDelete(@PathVariable int postId, @PathVariable int userId){
		int result = 0;
		if(postLikeRepository.findByPostIdAndUserId(postId, userId)!=null) {
			postLikeService.deleteLike(postId, userId);
			result = 1;
		}
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
