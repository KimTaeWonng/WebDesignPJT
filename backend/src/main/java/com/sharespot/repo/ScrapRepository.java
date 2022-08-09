package com.sharespot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.Scrap;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer>{
	
	public List<Scrap> findByUserId(int userid);
	
	public List<Scrap> findByPostId(int postId);
	
	public int deleteByPostId(int postId);
	

}
