package com.sharespot.repo;

import com.sharespot.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

//    Optional<Group> findByGroup_id(Integer id);
	
	public static final String FIND_GROUP_NAME = "SELECT * FROM group_table WHERE group_name like %:word% ";
	@Query(value = FIND_GROUP_NAME, nativeQuery = true)
	public List<Group> findByGroupNameContaining(@Param("word")String word);
	
	
	public static final String DELETE_GROUP_BY_GM = "DELETE * FROM group_table WHERE group_manager =:group_manager";
	@Transactional
	@Query(value = DELETE_GROUP_BY_GM, nativeQuery = true)
	public void deleteAllByGroupManager(@Param("group_manager") Integer group_manager);
	
	
	public static final String FIND_GROUP_ID = "SELECT group_id FROM group_table WHERE group_manager =:user_id ";
	@Query(value = FIND_GROUP_ID, nativeQuery = true)
	public List<Integer> findAllGroupIdByGroupManager(@Param("user_id")Integer user_id);
}
