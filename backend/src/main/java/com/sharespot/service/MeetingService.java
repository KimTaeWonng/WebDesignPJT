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

//    public List<Meeting> findAllMeetings(int groupNo){
//        return meetingRepository.findById(groupNo);
//    }
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

            meeting.setMeeting_title(value.getMeeting_title());
            meeting.setPost_lat(value.getPost_lat());
            meeting.setPost_lng(value.getPost_lng());
            meeting.setMeeting_url(value.getMeeting_url());
            meeting.setMeeting_money(value.getMeeting_money());
            meeting.setMeeting_day(value.getMeeting_day());
            meeting.setMeeting_people(value.getMeeting_people());
            meetingRepository.save(meeting);

            return 1;
        }
        return 0;
    }
}
