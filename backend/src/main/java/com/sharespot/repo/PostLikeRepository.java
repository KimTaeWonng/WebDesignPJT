package com.sharespot.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.PostLike;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer>{
	
	public List<PostLike> findByUserId(int userId);
	
	public List<PostLike> findByPostIdAndUserId(int postId, int userId);
	
	@Transactional
	public int deleteByPostIdAndUserId(int postId, int userId);

	@Transactional
	public void deleteAllByPostId(int postNo);

	@Transactional
	public void deleteAllByUserId(Integer userId);

}
