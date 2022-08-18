package com.sharespot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.Comment;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    public List<Comment> findByPostId(Integer postNo);

    @Transactional
	public void deleteAllByPostId(int postNo);

    @Transactional
	public void deleteAllByUserId(Integer user_id);
}
