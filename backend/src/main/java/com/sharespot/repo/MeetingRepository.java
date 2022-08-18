package com.sharespot.repo;

import com.sharespot.entity.Meeting;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
	public List<Meeting> findByGroupId(Integer groupId);

	public void deleteAllByGroupId(int gid);
	
	public static final String FIND_MEETING_ID = "SELECT meeting_id FROM meeting WHERE group_id =:group_id ";
	@Query(value = FIND_MEETING_ID, nativeQuery = true)
	public List<Integer> findAllMeetingIdByGroupId(@Param("group_id") int group_id);

	@Transactional
	public void deleteAllByGroupId(Integer gid);
}
