package com.sharespot.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.Scrap;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer>{
	
	public List<Scrap> findByUserId(int userid);
	
	public List<Scrap> findByPostId(int postId);
	
	public List<Scrap> findByPostIdAndUserId(int postId, int userId);
	
	@Transactional
	public int deleteByPostId(int postId);
	
	@Transactional
	public int deleteByPostIdAndUserId(int postId, int userId);

	@Transactional
	public void deleteAllByUserId(Integer user_id);

	@Transactional
	public void deleteAllByPostId(Integer postId);
	

}
