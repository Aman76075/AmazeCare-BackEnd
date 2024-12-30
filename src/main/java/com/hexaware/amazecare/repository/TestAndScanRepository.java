package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.model.TestAndScans;

public interface TestAndScanRepository extends JpaRepository<TestAndScans, Integer>{
	

}
