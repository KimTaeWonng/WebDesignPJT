package com.sharespot.controller;

import com.sharespot.entity.Meeting;
import com.sharespot.service.MeetingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
@AllArgsConstructor
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

}
