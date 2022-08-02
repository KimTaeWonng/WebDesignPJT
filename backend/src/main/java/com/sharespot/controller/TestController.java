package com.sharespot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharespot.entity.TestEntity;
import com.sharespot.repo.TestRepository;
import com.sharespot.service.TestService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequestMapping("api/test")
@RestController
@AllArgsConstructor
public class TestController {
	
	@Autowired
	private TestService testService;
	
	
	@PostMapping()
	public ResponseEntity<TestEntity> create() throws ParseException{
		TestEntity testEntity = TestEntity.builder().id(1).name("kim").build();
		
		TestEntity savedTest = testService.createTest(testEntity);
		return new ResponseEntity<TestEntity>(savedTest,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<TestEntity>> getTests(){
		List<TestEntity> tests = testService.getAll();
		return new ResponseEntity<>(tests,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<TestEntity>> getTest(@PathVariable int id){
		Optional<TestEntity> test = testService.getTest(id);
		
		return new ResponseEntity<>(test,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable int id) {
		testService.deleteTest(id);
		return new ResponseEntity<>(id,HttpStatus.OK);
	}
	
}
