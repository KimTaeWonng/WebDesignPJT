package com.sharespot.repo;

import com.sharespot.entity.Meeting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
	public List<Meeting> findByGroupId(Integer groupId);
}
