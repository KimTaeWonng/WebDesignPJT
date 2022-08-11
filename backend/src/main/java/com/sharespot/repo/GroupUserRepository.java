package com.sharespot.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.GroupUser;
import com.sharespot.entity.MeetingPeople;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Integer>{

	@Transactional
	int deleteByUserIdAndGroupId(int userId, int gid);
	
	public List<GroupUser> findByGroupId(int gid);

}
