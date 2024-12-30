package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Executive;
import com.hexaware.amazecare.repository.ExecutiveRepository;

@Service
public class ExecutiveService {
	@Autowired
	private ExecutiveRepository executiveRepository;

	public Executive getExecutive(Executive executive) {
		return executiveRepository.save(executive);
	}

	public Executive getExecutiveDetails(int id) {
		return executiveRepository.getExecutiveDetails(id);
	}

	public Executive getExecutiveDetailsById(int eid) {
		return executiveRepository.findById(eid).get();
	}

	public List<Executive> getAllExecutives() {
		return executiveRepository.findAll();
	}

}
