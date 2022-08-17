package com.sharespot.controller;

import com.sharespot.entity.Badge;
import com.sharespot.repo.BadgeRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/badge")
public class BadgeController {

    private final BadgeRepository badgeRepository;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @GetMapping("/{userId}")
    @ApiOperation(value = "뱃지조회", notes = "해당 userId의 <b>뱃지</b>를 반환한다.")
    public ResponseEntity<Optional<Badge>> getBadge(@PathVariable int userId){
        Optional<Badge> badge = badgeRepository.findById(userId);
        return new ResponseEntity<>(badge, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "뱃지생성", notes = "<b>뱃지</b>를 생성한다.")
    public ResponseEntity<Integer> createBadge(@RequestBody Badge badge){
        Badge badgeEntity = Badge.builder()
                .userId(badge.getUserId())
                .badgeFood(badge.getBadgeFood())
                .badgeCafe(badge.getBadgeCafe())
                .badgeCulture(badge.getBadgeCulture())
                .badgeTrip(badge.getBadgeTrip())
                .badgeLife(badge.getBadgeLife())
                .badgeGroup(badge.getBadgeGroup())
                .badgeFeed(badge.getBadgeFeed())
                .badgeComment(badge.getBadgeComment())
                .badgeMeet(badge.getBadgeMeet())
                .badgeFollow(badge.getBadgeFollow())
                .badgeFollower(badge.getBadgeFollower())
                .mainFood(badge.getMainFood())
                .mainCafe(badge.getMainCafe())
                .mainCulture(badge.getMainCulture())
                .mainTrip(badge.getMainTrip())
                .mainLife(badge.getMainLife())
                .mainGroup(badge.getMainGroup())
                .mainFeed(badge.getMainFeed())
                .mainComment(badge.getMainComment())
                .mainMeet(badge.getMainMeet())
                .mainFollow(badge.getMainFollow())
                .mainFollower(badge.getMainFollower())
                .build();

        int result = 1;
        try {
            badgeRepository.save(badgeEntity);
        }catch (Exception e){
            result = 0;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "뱃지수정(등급업)", notes = "요청한 RequestBody 안의 userId의 뱃지를 수정한다")
    public ResponseEntity<Integer> updateBadge(@RequestBody Badge badge){
        Badge badgeEntity = Badge.builder()
                .userId(badge.getUserId())
                .badgeFood(badge.getBadgeFood())
                .badgeCafe(badge.getBadgeCafe())
                .badgeCulture(badge.getBadgeCulture())
                .badgeTrip(badge.getBadgeTrip())
                .badgeLife(badge.getBadgeLife())
                .badgeGroup(badge.getBadgeGroup())
                .badgeFeed(badge.getBadgeFeed())
                .badgeComment(badge.getBadgeComment())
                .badgeMeet(badge.getBadgeMeet())
                .badgeFollow(badge.getBadgeFollow())
                .badgeFollower(badge.getBadgeFollower())
                .mainFood(badge.getMainFood())
                .mainCafe(badge.getMainCafe())
                .mainCulture(badge.getMainCulture())
                .mainTrip(badge.getMainTrip())
                .mainLife(badge.getMainLife())
                .mainGroup(badge.getMainGroup())
                .mainFeed(badge.getMainFeed())
                .mainComment(badge.getMainComment())
                .mainMeet(badge.getMainMeet())
                .mainFollow(badge.getMainFollow())
                .mainFollower(badge.getMainFollower())
                .build();

        int result = 1;
        try {
            badgeRepository.save(badgeEntity);
        }catch (Exception e){
            result = 0;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "뱃지삭제(회원탈퇴)", notes = "userId의 뱃지 데이터를 삭제한다")
    public ResponseEntity<Integer> deleteBadge(@PathVariable int userId){
        int result = 1;
        try {
            badgeRepository.deleteById(userId);
        }catch (Exception e){
            result = 0;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //뱃지삭제



}
