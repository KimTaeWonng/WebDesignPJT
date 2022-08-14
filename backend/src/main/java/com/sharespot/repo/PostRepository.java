package com.sharespot.repo;


import com.sharespot.entity.Post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PostRepository extends JpaRepository<Post, Integer> {

    public static final String FIND_USER_POST = "SELECT post_id, class_big, post_lat, post_lng, image FROM post_table WHERE user_id = :userId order by post_id desc";

    @Query(value = FIND_USER_POST, nativeQuery = true)
    public List<Object[]> findByUserId(@Param("userId") int userId);

    public Optional<Post> findByPostId(int postId);

    
    public List<Post> findByUserIdOrderByPostIdDesc(int userId);
    
    public List<Post> findAllByOrderByUploadTimeDesc();

	public List<Post> findAllByOrderByLikeCntDesc();

}
