package com.sharespot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer>{
	
	public List<PostLike> findByPostIdAndUserId(int postId, int userId);
	
	public int deleteByPostIdAndUserId(int postId, int userId);

}
