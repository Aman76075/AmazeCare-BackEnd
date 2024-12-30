package com.hexaware.amazecare.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{

	@Query("Select r from MedicalRecord r JOIN r.patient p where p.id=?1")
	Page<MedicalRecord> findAllRecordById(int pid,Pageable pageable);

}
