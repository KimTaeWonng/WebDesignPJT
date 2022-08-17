package com.sharespot.repo;

import com.sharespot.entity.Badge;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {

	@Transactional
	void deleteAllByUserId(Integer user_id);
}
