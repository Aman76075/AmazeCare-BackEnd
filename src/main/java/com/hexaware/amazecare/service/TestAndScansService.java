package com.hexaware.amazecare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.repository.TestAndScanRepository;

@Service
public class TestAndScansService {
	@Autowired
	private TestAndScanRepository testAndScanRepository;

	public TestAndScans insert(TestAndScans testAndScans) {
		return testAndScanRepository.save(testAndScans);
	}

	public TestAndScans validate(int tid) throws ResourceNotFoundException {
		Optional<TestAndScans>optional=testAndScanRepository.findById(tid);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Test Id not found");
		}
		return optional.get();
	}

}
