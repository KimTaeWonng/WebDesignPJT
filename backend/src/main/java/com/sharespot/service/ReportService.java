package com.sharespot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sharespot.entity.Report;
import com.sharespot.repo.ReportRepository;
import com.sharespot.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
	
	private final ReportRepository reportRepository;
	
	public List<Report> getAll(){
		return reportRepository.findAll();
	}
	
	public Report create(Report report) {
		Report savedreport = reportRepository.save(report);
		return savedreport;
	}

}
