package com.sharespot.controller;

import com.sharespot.entity.Badge;
import com.sharespot.repo.BadgeRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/badge")
public class BadgeController {

    private final BadgeRepository badgeRepository;

    @GetMapping("/{userId}")
    @ApiOperation(value = "뱃지조회", notes = "해당 userId의 <b>뱃지</b>를 반환한다.")
    public ResponseEntity<Optional<Badge>> getBadge(@PathVariable int userId){
        Optional<Badge> badge = badgeRepository.findById(userId);
        return new ResponseEntity<>(badge, HttpStatus.OK);
    }


}
