package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.MedicalRecord;
import com.hexaware.amazecare.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord insert(MedicalRecord medicalRecord) {
		return medicalRecordRepository.save(medicalRecord);
	}

	public Page<MedicalRecord> getAllMedicalRecordwithId(int pid, Pageable pageable) {
		return medicalRecordRepository.findAllRecordById(pid,pageable);
	}

}
