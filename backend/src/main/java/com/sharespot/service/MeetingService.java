package com.sharespot.service;

import com.sharespot.entity.Meeting;
import com.sharespot.repo.MeetingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public List<Meeting> findAllMeetings(Integer groupNo){    	
    	return meetingRepository.findByGroupId(groupNo);
    }
    
    public Optional<Meeting> findByMeetingId(Integer mid){
        return meetingRepository.findById(mid);
    }
    public Meeting createMeeting(Meeting meeting){
        Meeting savedMeeting = meetingRepository.save(meeting);
        return savedMeeting;
    }
    public int deleteMeeting(Integer mid){
        meetingRepository.deleteById(mid);
        return 1;
    }
    public int updateMeeting(Integer mid, Meeting value){
        Optional<Meeting> mMeeting = meetingRepository.findById(mid);
        if(mMeeting.isPresent()){
            Meeting meeting = mMeeting.get();

            meeting.setMeetingTitle(value.getMeetingTitle());
            meeting.setMeetingLat(value.getMeetingLat());
            meeting.setMeetingLng(value.getMeetingLng());
            meeting.setMeetingUrl(value.getMeetingUrl());
            meeting.setMeetingMoney(value.getMeetingMoney());
            meeting.setMeetingDay(value.getMeetingDay());
            meeting.setMeetingPeople(value.getMeetingPeople());
            meetingRepository.save(meeting);

            return 1;
        }
        return 0;
    }
}
