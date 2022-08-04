package com.sharespot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharespot.entity.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Integer>{

}
