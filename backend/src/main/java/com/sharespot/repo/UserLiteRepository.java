package com.sharespot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.UserLite;

@Repository
public interface UserLiteRepository extends JpaRepository<UserLite, Integer>{
	
	public List<UserLite> findById(int id); 
}
