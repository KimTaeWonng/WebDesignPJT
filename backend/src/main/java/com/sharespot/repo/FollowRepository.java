package com.sharespot.repo;

import com.sharespot.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    public List<Follow> findByUserId(Integer userId);
    public List<Follow> findByFollowerId(Integer followerId);

    public Optional<Follow> findByUserIdAndFollowerId(Integer userId, Integer followerId);

    @Transactional
    public Integer deleteByUserIdAndFollowerId(Integer userId, Integer followerId);
    @Transactional
	public void deleteAllByUserId(Integer user_id);
    @Transactional
	public void deleteAllByFollowerId(Integer user_id);
}
