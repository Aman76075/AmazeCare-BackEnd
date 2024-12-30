package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.repository.LabOperatorRepository;

@Service
public class LabOperatorService {
	@Autowired
	private LabOperatorRepository labOperatorRepository;

	public LabOperator getOperator(LabOperator labOperator) {
		return labOperatorRepository.save(labOperator);
	}

	public LabOperator validate(int lid) throws ResourceNotFoundException {
		Optional<LabOperator>optional=labOperatorRepository.findById(lid);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid LabOperator Id");
		}
		return optional.get();
	}

	public List<LabOperator> getAllLabOperators() {
		return labOperatorRepository.findAll();
	}

}
