package com.hexaware.amazecare.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.dto.UpdateDetailsDto;
import com.hexaware.amazecare.enums.Department;
import com.hexaware.amazecare.enums.Role;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.Executive;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.ExecutiveService;
import com.hexaware.amazecare.service.LabOperatorService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ExecutiveController {
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;
	@Autowired
	private LabOperatorService labOperatorService;
	Logger logger = LoggerFactory.getLogger(ExecutiveController.class);

	@GetMapping("/api/doctor/all/page")
	public Page<Doctor> getAllDoctor(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "5000") int size, Principal principal) {
		logger.info("API Accessed by " + principal.getName());
		Pageable pageable = PageRequest.of(page, size);
		logger.info("Fetching all inpatient using pageable...");
		if (size == 5000)
			logger.warn("Fetching inpatient without limit ");
		return doctorService.getAllDoctor(pageable);
	}

	@GetMapping("/api/doctor/all")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/department/all")
	public List<Department> getAllDepartment() {
		return Arrays.asList(Department.values());
	}

	@PostMapping("/auth/sign-up/doctor")
	public ResponseEntity<?> doctorSignUp(@RequestBody Doctor doctor, ResponseMessageDto dto, Principal principal) {
		logger.info("API accessed by " + principal.getName());
		try {
			User user = doctor.getUser();

			user.setRole(Role.DOCTOR);
			user = userService.signup(user);
			doctor.setUser(user);
			doctor.setJoiningDate(LocalDate.now());
			doctor = doctorService.insert(doctor);
			return ResponseEntity.ok(doctor);

		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/api/labOperator/all")
	public List<LabOperator> getAllLabOperators() {
		return labOperatorService.getAllLabOperators();
	}

	@PostMapping("/auth/sign-up/lab-operator")
	public ResponseEntity<?> labOperatorSignUp(@RequestBody LabOperator labOperator, ResponseMessageDto dto,
			Principal principal) {
		logger.info("API accessed by " + principal.getName());
		try {
			User user =labOperator.getUser();
			user.setRole(Role.LAB_OPERATOR);
			user = userService.signup(user);
			labOperator.setUser(user);
			labOperator.setJoinedOn(LocalDate.now());
			labOperator = labOperatorService.getOperator(labOperator);
			return ResponseEntity.ok(labOperator);
		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	@GetMapping("/api/executive/getDetailsById/{eid}")
	public Executive getExecutiveDetails(@PathVariable int eid) {
		return executiveService.getExecutiveDetailsById(eid);
	}
	@PostMapping("/api/executive/update/{eid}")
	public Executive updateExecutive(@PathVariable int eid,@RequestBody UpdateDetailsDto dto) {
		Executive exe=executiveService.getExecutiveDetailsById(eid);
		exe.setContact(dto.getContact());
		exe.setEmail(dto.getEmail());
		return executiveService.getExecutive(exe);
	}

}
