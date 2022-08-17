package com.sharespot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.GroupLite;

@Repository
public interface GroupLiteRepository extends JpaRepository<GroupLite, Integer>{
	
	List<GroupLite> findByGroupNameContaining(String word);

}
