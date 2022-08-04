package com.sharespot.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sharespot.entity.Alarm;
import com.sharespot.entity.User;
import com.sharespot.repo.AlarmRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmService {
	
	private final AlarmRepository alarmRepository;
	
	public int modify(int id, Alarm value) {
		Optional<Alarm> mAlarm = alarmRepository.findById(id);
		if(mAlarm.isPresent()) {
			Alarm uAlarm = mAlarm.get();
			
			uAlarm.setIs_heart(value.getIs_heart());
			uAlarm.setIs_comment(value.getIs_comment());
			uAlarm.setIs_follow(value.getIs_follow());
			uAlarm.setIs_group(value.getIs_group());
			uAlarm.setIs_feed(value.getIs_feed());
			uAlarm.setIs_chat(value.getIs_chat());
			alarmRepository.save(uAlarm);
			
			return 1;
		}
		return 0;
	}

}
