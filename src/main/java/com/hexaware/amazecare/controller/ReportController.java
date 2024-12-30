package com.hexaware.amazecare.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.ReportDetailsDto;
import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.ReportService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ReportController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private ReportService reportService;
	Logger logger = LoggerFactory.getLogger(ReportController.class);

	@GetMapping("/reports/fetch/{pid}")
	public Page<ReportDetailsDto> fetchReports(@PathVariable int pid,
			@RequestParam(required = false, defaultValue = "0") String page,
			@RequestParam(required = false, defaultValue = "1000000")String size,Principal principal) throws Exception{
		Pageable pageable=null;
		try {
			pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		} catch (Exception e) {
			throw e;
		}
		return reportService.fetchReport(pid,pageable);
		
		
	}
	

}
