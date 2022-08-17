package com.sharespot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.Post;
import com.sharespot.entity.PostLite;
import com.sharespot.entity.User;
import com.sharespot.repo.PostRepository;
import com.sharespot.repo.UserRepository;
import com.sharespot.service.JwtServiceImpl;
import com.sharespot.service.PostService;
import com.sharespot.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/search")
public class CurationController {
	
	
	@Autowired
	private PostService postService;
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@GetMapping("/posts/category/{big}/{small}")
	@ApiOperation(value = "큐레이션 탐색", notes = "대분류와 소분류는 무조건 넣어야함(소분류부터 복수 선택 가능)")
	public ResponseEntity<Page<Post>> curationList(@RequestParam("page") int page, @RequestParam("size") int size, @PathVariable String big, @RequestParam String[] small, @RequestParam(name = "who", required = false, defaultValue = "혼자, 친구, 가족, 연인") String[] who, @RequestParam (name = "where", required = false, defaultValue = "서울, 경기, 인천, 강원, 제주, 대전, 충북, 충남/세종, 부산, 울산, 경남, 대구, 경북, 광주, 전남, 전주/전북" )String[] where){
		
		List<Post> curationResult = postService.CurationList(big, small, who, where);
		
		Pageable pageable = PageRequest.of(page, size,Sort.Direction.DESC,"postId");
		final int start = (int)pageable.getOffset();
		final int end = Math.min((start+pageable.getPageSize()), curationResult.size());
		log.info(start+" "+end);
		final Page<Post> pagedPost = new PageImpl<Post>(curationResult.subList(start, end), pageable, curationResult.size());
		
		return new ResponseEntity<Page<Post>>(pagedPost,HttpStatus.OK);
	}

	
	@GetMapping()
	@ApiOperation(value = "기본조회", notes = "기본은 최신순 정렬이다")
	public ResponseEntity<List<Post>> curationDefault(){
		List<Post> curationResult = postService.defaultList();
		
		return new ResponseEntity<List<Post>>(curationResult,HttpStatus.OK);
	}
	
	@GetMapping("/posts/count/{userId}")
	@ApiOperation(value = "큐레이션 갯수 탐색", notes = "게시글의 각 대분류 갯수를 가져온다.")
	public ResponseEntity<HashMap<String,Integer>> curationCount(@PathVariable int userId){
		
		
		
		HashMap<String, Integer> hsm = new HashMap<String, Integer>();
		
		hsm.put("맛집", postRepository.countByClassBig(userId, "맛집"));
		hsm.put("카페", postRepository.countByClassBig(userId, "카페"));
		hsm.put("문화", postRepository.countByClassBig(userId, "문화"));
		hsm.put("여행", postRepository.countByClassBig(userId, "여행"));
		hsm.put("생활", postRepository.countByClassBig(userId, "생활"));
		
		return new ResponseEntity<HashMap<String,Integer>>(hsm,HttpStatus.OK);			
	}
	
	@GetMapping("/posts/hotspot/{word}")
	@ApiOperation(value = "핫스팟", notes = "이달의 핫스팟 설정")
	public ResponseEntity<List<PostLite>> hotSpot(@PathVariable String word){
		
		List<Post> post = postRepository.findByClassBigOrderByLikeCntDesc(word);
		
		List<User> user = userRepository.findByUserGradeGreaterThanEqual(0);
		
		List<PostLite> hotPost = new ArrayList<PostLite>();
		
		for(Post p : post) {
			
			for(User u : user)
			if(u.getUser_id()==p.getUserId()) {
				
				PostLite temp = PostLite.builder()
								.postId(p.getPostId())
								.userId(p.getUserId())
								.nickname(p.getNickname())
								.content(p.getContent())
								.postGpsName(p.getPostGpsName())
								.image(p.getImage()).build();
				
				
				hotPost.add(temp);
				break;
			}
			if(hotPost.size()>=5) {
				break;
			}
		}
		
		return new ResponseEntity<List<PostLite>>(hotPost,HttpStatus.OK);
		
	}
}
