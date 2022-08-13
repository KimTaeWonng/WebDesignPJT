package com.sharespot.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.User;
import com.sharespot.entity.UserLite;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	Optional<User> findByemail(String email);
	User findByEmailAndPassword(String email, String password);
	List<User> findByNicknameContainingOrIntroduceContaining(String searchWord,String searchWord2);
	List<User> findByUserGradeGreaterThanEqual(int usergrade);
	
	boolean existsById(int id);


	public static final String FIND_USER_PAGE = "SELECT name, nickname, introduce, profile_image, BD, user_grade, register_time from user_table where user_id = :userId";
	@Query(value = FIND_USER_PAGE, nativeQuery = true)
	public Object[] findByUserId(@Param("userId") int userId);
}


