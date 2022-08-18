package com.sharespot.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.MeetingPeople;

@Repository
public interface MPRepository extends JpaRepository<MeetingPeople, Integer>{
	
	@Transactional
	public int deleteByUserIdAndMeetingId(int userId, int mid);
	
	public List<MeetingPeople> findByMeetingId(int mid);

	@Transactional
	public void deleteAllByMeetingId(int mid);

	@Transactional
	public void deleteAllByUserId(Integer user_id);

}
