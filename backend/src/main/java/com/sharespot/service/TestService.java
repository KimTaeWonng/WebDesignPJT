package com.sharespot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sharespot.entity.TestEntity;
import com.sharespot.repo.TestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public TestEntity createTest(TestEntity testEntity) {
		TestEntity savedTest = testRepository.save(testEntity);
		return savedTest;
	}
	
//	public TestEntity updateMember(TestEntity testEntity) {
//		TestEntity updatedTest = null;
//        try {
//        	Optional<TestEntity> existTest = getTest(testEntity.getId());
//            if (!ObjectUtils.isEmpty(existTest)) {
//                updatedTest = TestRepository.save(testEntity);  // JpaRepository���� �����ϴ� save() �Լ�
//            }
//        } catch (Exception e) {
//            log.info("[Fail] e: " + e.toString());
//        } finally {
//            return updatedTest;
//        }
//    }

    public List<TestEntity> getAll(){
        return testRepository.findAll();
    }
	
	public Optional<TestEntity> getTest(Integer id) {
        return testRepository.findById(id);  // JpaRepository���� �����ϴ� getById() �Լ�
    }
	
	public void deleteTest(Integer id) {
        testRepository.deleteById(id);  // JpaRepository���� �����ϴ� deleteById() �Լ�
    }
	
}
