package com.sharespot.repo;

import com.sharespot.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage,BigInteger> {
    List<PostImage> findAllByPostId(int postId);
}
