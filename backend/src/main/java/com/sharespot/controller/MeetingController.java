package com.sharespot.controller;

import com.sharespot.entity.Meeting;
import com.sharespot.repo.MeetingRepository;
import com.sharespot.service.MeetingService;
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

    @GetMapping("/{groupNo}/meetings")
    public ResponseEntity<List<Meeting>> getAllMeeting(@PathVariable int groupNo){
        List<Meeting> meetings = meetingService.findAllMeetings(groupNo);
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }
    
    @GetMapping("/{groupNo}/meetings/{mid}")
    public ResponseEntity<Optional<Meeting>> getMeeting(@PathVariable int groupNo, int mid){
        Optional<Meeting> meetings = meetingService.findByMeetingId(mid);
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @PostMapping("/{groupNo}/meetings")
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
    public ResponseEntity<Integer> deleteMeeting(@PathVariable int groupNo, int mid){
        return new ResponseEntity<Integer>(meetingService.deleteMeeting(mid), HttpStatus.OK);
    }
    
    
}
