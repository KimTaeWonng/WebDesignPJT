package com.sharespot.controller;

import com.sharespot.entity.Meeting;
import com.sharespot.entity.MeetingPeople;
import com.sharespot.entity.User;
import com.sharespot.repo.MPRepository;
import com.sharespot.repo.MeetingRepository;
import com.sharespot.service.MPService;
import com.sharespot.service.MeetingService;
import com.sharespot.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;
    
    @Autowired
    private MPRepository mpRepository;
    @Autowired
    private MPService mpService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/{groupNo}/meetings")
    @ApiOperation(value = "정모목록", notes = "해당 그룹의 <b>정기모임 전체 목록</b>을 반환한다.")
    public ResponseEntity<List<Meeting>> getAllMeeting(@PathVariable int groupNo){
        List<Meeting> meetings = meetingService.findAllMeetings(groupNo);
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }
    
    @GetMapping("/{groupNo}/meetings/{mid}")
    @ApiOperation(value = "정모", notes = "해당 <b>정기모임</b>을 반환한다.")
    public ResponseEntity<Optional<Meeting>> getMeeting(@PathVariable int groupNo, @PathVariable int mid){
        Optional<Meeting> meetings = meetingService.findByMeetingId(mid);
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @PostMapping("/{groupNo}/meetings")
    @ApiOperation(value = "정모작성", notes = "<b>정기모임</b>을 작성한다.")
    public ResponseEntity<Integer> createMeeting(@PathVariable int groupNo, @RequestBody Meeting meeting){
        Meeting meetingEntity = Meeting.builder()
        		.groupId(meeting.getGroupId())
        		.meetingLat(meeting.getMeetingLat())
        		.meetingLng(meeting.getMeetingLng())
        		.meetingDay(meeting.getMeetingDay())
        		.meetingMoney(meeting.getMeetingMoney())
        		.meetingPeople(meeting.getMeetingPeople())
        		.meetingTitle(meeting.getMeetingTitle())
        		.meetingUrl(meeting.getMeetingUrl())
        		.build();
        
        meetingService.createMeeting(meetingEntity);
    	
    	return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }
    
    @PutMapping("/{groupNo}/meetings/{mid}")
    @ApiOperation(value = "정모수정", notes = "해당 <b>정기모임</b>을 수정한다.")
    public ResponseEntity<Integer> updateMeeting(@PathVariable int groupNo, int mid, @RequestBody Meeting meeting){
        Meeting meetingEntity = Meeting.builder()
        		.groupId(meeting.getGroupId())
        		.meetingLat(meeting.getMeetingLat())
        		.meetingLng(meeting.getMeetingLng())
        		.meetingDay(meeting.getMeetingDay())
        		.meetingMoney(meeting.getMeetingMoney())
        		.meetingPeople(meeting.getMeetingPeople())
        		.meetingTitle(meeting.getMeetingTitle())
        		.meetingUrl(meeting.getMeetingUrl())
        		.build();

        int result = meetingService.updateMeeting(meeting.getMeetingId(), meetingEntity);
    	
    	return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/{groupNo}/meetings/{mid}")
    @ApiOperation(value = "정모삭제", notes = "해당 <b>정기모임</b>을 삭제한다.")
    public ResponseEntity<Integer> deleteMeeting(@PathVariable int groupNo, int mid){
        return new ResponseEntity<Integer>(meetingService.deleteMeeting(mid), HttpStatus.OK);
    }
    
    
    @GetMapping("/meetings/members/{mid}")
    @ApiOperation(value = "정모 참가자 확인", notes = "해당 정모에 참가하는 사람들 리스트 확인")
    public ResponseEntity<List<MeetingPeople>> MPList(@PathVariable int mid){
    	
    	List<MeetingPeople> mpList = mpRepository.findByMeetingId(mid);
    	
    	return new ResponseEntity<List<MeetingPeople>>(mpList,HttpStatus.OK);
    	
    }
    
    
    @PostMapping("/meetings/members/{mid}/{userId}")
    @ApiOperation(value = "정모참가", notes = "해당 정모에 참가한다")
    public ResponseEntity<MeetingPeople> joinMeeting(@PathVariable int mid, @PathVariable int userId){
    	
    	Optional<User> user = userService.getUser(userId); //Id로 해당 유저 정보 찾아옴
    	User userEntity = user.get(); //Get 함수 사용하기 위해 바꿔주고
    	
    	MeetingPeople mp = MeetingPeople.builder().meetingId(mid).userId(userEntity.getUser_id()).userNick(userEntity.getNickname()).build();
    	
    	MeetingPeople savedMp = mpRepository.save(mp);
    	
    	return new ResponseEntity<MeetingPeople>(savedMp,HttpStatus.OK);
    	
    }
    
    @DeleteMapping("/meetings/members/{mid}/{userId}")
    @ApiOperation(value = "정모 참가취소", notes = "해당 정모에 탈퇴한다")
    public ResponseEntity<Integer> exitMeeting(@PathVariable int mid, @PathVariable int userId){
    	
    	int result = 0;
    	
    	try {
    		mpService.deleteJoin(userId, mid);
    		result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<Integer>(result,HttpStatus.OK);
    	
    }
    
    
    
}
