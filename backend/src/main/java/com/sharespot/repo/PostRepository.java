package com.sharespot.repo;


import com.sharespot.entity.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PostRepository extends JpaRepository<Post, Integer> {

    public static final String FIND_USER_POST = "SELECT post_id, image FROM post_table WHERE user_id = :userId";

    @Query(value = FIND_USER_POST, nativeQuery = true)
    public List<Object[]> findByUserId(int userId);
    
    public List<Post> findAllByOrderByUploadTimeDesc();

}
