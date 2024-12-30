package com.hexaware.amazecare.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.repository.PatientRepository;


@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	public Patient insert(Patient patient) {
		return patientRepository.save(patient);
		
	}
	public Patient validate(int patientId) throws ResourceNotFoundException {
		Optional<Patient>optional=patientRepository.findById(patientId);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Patient Id Given");
		}
		Patient patient=optional.get();
		return patient;
	}
	public Patient getPatientByUserId(int id) {
		return patientRepository.getPatientByUserId(id);
	}

}
