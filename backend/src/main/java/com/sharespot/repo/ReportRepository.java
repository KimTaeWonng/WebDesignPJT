package com.sharespot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharespot.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

}
