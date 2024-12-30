package com.hexaware.amazecare.controller;

import java.security.Principal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.MedicalRecord;
import com.hexaware.amazecare.service.MedicalRecordService;
import com.hexaware.amazecare.service.PatientService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class MedicalRecordController {
	@Autowired
	private MedicalRecordService medicalRecordService;
	@Autowired
	private PatientService patientService;
	Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	/* Gettingmedical record by patient id */
	/*
	 * @GetMapping("/medicalrecord/{pid}") public ResponseEntity<?>
	 * getMedicalRecord(@PathVariable int pid, ResponseMessageDto dto,Principal
	 * principal) { logger.info("API accessed by "+principal.getName());
	 * List<MedicalRecord> list = new ArrayList<>(); try {
	 * patientService.validate(pid); list =
	 * medicalRecordService.getAllMedicalRecordwithId(pid); return
	 * ResponseEntity.ok(list); } catch (ResourceNotFoundException e) {
	 * dto.setMsg(e.getMessage()); return ResponseEntity.badRequest().body(dto); }
	 * 
	 * }
	 */
	

}
