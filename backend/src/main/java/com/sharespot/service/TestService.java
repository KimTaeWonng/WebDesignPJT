package com.sharespot.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sharespot.entity.TestEntity;
import com.sharespot.repo.TestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
	private final TestRepository testRepository;
	
	public TestEntity createTest(TestEntity testEntity) {
		TestEntity savedTest = testRepository.save(testEntity);
		return savedTest;
	}
	
	public TestEntity updateMember(TestEntity testEntity) {
		TestEntity updatedTest = null;
        try {
        	TestEntity existTest = getTest(testEntity.getId());
            if (!ObjectUtils.isEmpty(existTest)) {
                updatedTest = TestRepository.save(testEntity);  // JpaRepository���� �����ϴ� save() �Լ�
            }
        } catch (Exception e) {
            log.info("[Fail] e: " + e.toString());
        } finally {
            return updatedTest;
        }
    }

    public List<TestEntity> getAll(){
        return testRepository.findAll();
    }
	
	public TestEntity getTest(Integer id) {
        return testRepository.getById(id);  // JpaRepository���� �����ϴ� getById() �Լ�
    }
	
	public void deleteTest(Integer id) {
        testRepository.deleteById(id);  // JpaRepository���� �����ϴ� deleteById() �Լ�
    }
	
}
