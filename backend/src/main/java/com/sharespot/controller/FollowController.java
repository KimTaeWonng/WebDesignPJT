package com.sharespot.controller;

import com.sharespot.entity.Follow;
import com.sharespot.entity.UserLite;
import com.sharespot.repo.FollowRepository;
import com.sharespot.repo.UserLiteRepository;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}")
public class FollowController {

    private final FollowRepository followRepository;
    
    private final UserLiteRepository userLiteRepository;

    @GetMapping("/follower")
    @ApiOperation(value = "팔로워 목록조회", notes = "해당 userId의 <b>팔로워목록</b>을 반환한다.")
    public ResponseEntity<List<Optional<UserLite>>> getFollower(@PathVariable int userId) {
    	
        List<Follow> follower = followRepository.findByUserId(userId);
        
        List<Optional<UserLite>> followerInfo = new ArrayList<>();
        
        for(Follow f : follower) {
        	
        	Optional<UserLite> temp = userLiteRepository.findById(f.getFollowerId());
        	
        	followerInfo.add(temp);
        }
        
        return new ResponseEntity<>(followerInfo, HttpStatus.OK);
    }
    @GetMapping("/following")
    @ApiOperation(value = "팔로잉 목록조회", notes = "해당 userId가 팔로우하는 <b>팔로잉목록</b>을 반환한다.")
    public ResponseEntity<List<Optional<UserLite>>> getFollowing(@PathVariable int userId) {
        List<Follow> following = followRepository.findByFollowerId(userId);

        List<Optional<UserLite>> followingInfo = new ArrayList<>();

        for(Follow f : following){

            Optional<UserLite> temp = userLiteRepository.findById(f.getFollowerId());

            followingInfo.add(temp);
        }

        return new ResponseEntity<>(followingInfo, HttpStatus.OK);
    }

    @PostMapping("/follow")
    @ApiOperation(value = "팔로우하기", notes = "followerId가 userId를 팔로우한다.")
    public ResponseEntity<Integer> createFollow(@RequestBody Follow follow ,@PathVariable int userId) {
        Follow followEntity = Follow.builder()
                .userId(follow.getUserId())
                .followerId(follow.getFollowerId())
                .build();

        int result = 1;
        try {
            followRepository.save(followEntity);
        }catch (Exception e){
            result = 0;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{followerId}")
    @ApiOperation(value = "팔로우 취소하기", notes = "followerId가 userId를 팔로우 취소한다.")
    public ResponseEntity<Integer> deleteFollow(@PathVariable int userId, @PathVariable int followerId){
        int result = 0;
        if(followRepository.findByUserIdAndFollowerId(userId,followerId).isPresent()){
            followRepository.deleteByUserIdAndFollowerId(userId,followerId);
            result = 1;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
