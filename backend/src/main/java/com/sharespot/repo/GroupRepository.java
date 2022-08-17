package com.sharespot.repo;

import com.sharespot.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

//    Optional<Group> findByGroup_id(Integer id);
	
	public static final String FIND_GROUP_NAME = "SELECT group_id, group_name, group_gender, group_age_min, group_age_max FROM group_table WHERE group_name like %:word% ";
	@Query(value = FIND_GROUP_NAME, nativeQuery = true)
	public List<Object[]> findByGroupNameContaining(@Param("word")String word);
}
