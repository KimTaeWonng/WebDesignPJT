package com.sharespot.repo;

import com.sharespot.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

//    Optional<Group> findByGroup_id(Integer id);

}
