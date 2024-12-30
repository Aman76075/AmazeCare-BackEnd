package com.hexaware.amazecare.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.LabOperatorService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.ReportService;
import com.hexaware.amazecare.service.TestAndScansService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class LabOperatorController {
	@Autowired
	private LabOperatorService labOperatorService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private TestAndScansService testAndScansService;
	@Autowired
    private ReportService reportService;

	@PostMapping("/laboperator/onboard")
	public LabOperator onboardOperator(@RequestBody LabOperator labOperator) {
		User user = new User();
		user.setUsername(labOperator.getUser().getUsername());
		user.setPassword(labOperator.getUser().getPassword());
		user.setRole(labOperator.getUser().getRole());
		user = userService.insert(user);
		labOperator.setUser(user);
		labOperator.setJoinedOn(LocalDate.now());
		return labOperatorService.getOperator(labOperator);
	}
	@PostMapping("laboperator/generateReport/{lid}/{pid}/{did}/{tid}")
	public ResponseEntity<?> generateReport(@RequestBody Report report,@PathVariable int lid,@PathVariable int pid,@PathVariable int did,@PathVariable int tid,ResponseMessageDto dto) {
		Doctor doctor=null;
	    try {
	    	doctor=doctorService.validate(did);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    Patient patient=null;
	    try {
	    	patient=patientService.validate(pid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    TestAndScans testAndScans=null;
	    try {
	    	testAndScans=testAndScansService.validate(tid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    LabOperator labOperator=null;
	    try {
	    	labOperator=labOperatorService.validate(lid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    report.setDoctor(doctor);
	    report.setPatient(patient);
	    report.setLabOperator(labOperator);
	    report.setTestAndScans(testAndScans);
	    report.setGeneratedOn(LocalDate.now());
	    report.setScanTestType(testAndScans.getType());
	    report=reportService.addReport(report);
	    return ResponseEntity.ok(report);
	}

}
