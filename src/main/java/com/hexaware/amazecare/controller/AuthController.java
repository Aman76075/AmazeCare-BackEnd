package com.hexaware.amazecare.controller;

import java.io.IOException;

import java.security.Principal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.amazecare.JwtUtil;
import com.hexaware.amazecare.dto.JwtDto;
import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.enums.PatientType;
import com.hexaware.amazecare.enums.Role;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Admin;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.Executive;
import com.hexaware.amazecare.model.InPatient;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.OutPatient;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.AdminService;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.ExecutiveService;
import com.hexaware.amazecare.service.InPatientService;
import com.hexaware.amazecare.service.LabOperatorService;
import com.hexaware.amazecare.service.OutPatientService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.UserSecurityService;
import com.hexaware.amazecare.service.UserService;
import com.hexaware.amazecare.controller.AuthController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserSecurityService userSecurityService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private InPatientService inPatientService;
	@Autowired
	private OutPatientService outPatientService;
	@Autowired
	private LabOperatorService labOperatorService;
	@Autowired
	private ExecutiveService executiveService;
	@Autowired
	private AdminService adminService;
	Logger logger = LoggerFactory.getLogger(AuthController.class);

	
	@PostMapping("/api/token")
	public ResponseEntity<?> getToken(@RequestBody User user, JwtDto dto ) {
		try {
		Authentication auth 
				= new UsernamePasswordAuthenticationToken
							(user.getUsername(), user.getPassword());
		
		authenticationManager.authenticate(auth);
		
		/*Check if username is in DB */
		user = (User) userSecurityService.loadUserByUsername(user.getUsername());
		
		String jwt = jwtUtil.generateToken(user.getUsername());
		dto.setUsername(user.getUsername());
		dto.setToken(jwt);
		return ResponseEntity.ok(dto);
		}
		catch(AuthenticationException ae) {
			return ResponseEntity.badRequest().body(ae.getMessage());
		}
	}

	@PostMapping("/auth/sign-up/inPatient")
	public ResponseEntity<?> inPatientSignUp(@RequestBody InPatient inPatient, ResponseMessageDto dto) {
		try {
			User user=new User();
			user.setUsername(inPatient.getPatient().getUser().getUsername());
			user.setPassword(inPatient.getPatient().getUser().getPassword());
			user.setRole(Role.IN_PATIENT);
			user = userService.signup(user);
			
			Patient patient=new Patient();
			patient.setUser(user);
			patient.setPatientType(PatientType.IN_PATIENT);
			
			patient=patientService.insert(patient);
			
			inPatient.setPatient(patient);
			
			inPatient= inPatientService.insert(inPatient);	
			return ResponseEntity.ok(inPatient);

		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	@PostMapping("/auth/sign-up/outPatient")
	public ResponseEntity<?>outPatientSignup(@RequestBody OutPatient outPatient,ResponseMessageDto dto){
		try {
			User user=new User();
			user.setUsername(outPatient.getPatient().getUser().getUsername());
			user.setPassword(outPatient.getPatient().getUser().getPassword());
			user.setRole(outPatient.getPatient().getUser().getRole());
			user = userService.signup(user);
			
			Patient patient=new Patient();
			patient.setUser(user);
			patient.setPatientType(outPatient.getPatient().getPatientType());
			
			patient=patientService.insert(patient);
			
			outPatient.setPatient(patient);
			
			outPatient= outPatientService.insert(outPatient);	
			return ResponseEntity.ok(outPatient);

		} catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/auth/sign-up/executive")
	public ResponseEntity<?> executiveSignUp(@RequestBody Executive executive,ResponseMessageDto dto){
		try {
		User user = new User();
		user.setUsername(executive.getUser().getUsername());
		user.setPassword(executive.getUser().getPassword());
		user.setRole(Role.EXECUTIVE);
		user = userService.signup(user);
		executive.setUser(user);
		executive.setJoinedOn(LocalDate.now());
		executive=executiveService.getExecutive(executive);
		return ResponseEntity.ok(executive);
		}catch (InvalidUsernameException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

	@GetMapping("/api/hello")
	public String sayHello(Principal principal) {
		String user = "";
		if(principal == null) {
			user = "TEMP_USER";
		}
		else {
			user = principal.getName();
		}
		return "api accessed by: " + user;
	}
	
	@GetMapping("/api/doctor/hello")
	public String sayHelloDoc(Principal principal) {
		String user = "";
		if(principal == null) {
			user = "TEMP_USER";
		}
		else {
			user = principal.getName();
		}
		return "api accessed by: " + user;
	}
	@PostMapping("/api/inPatient/batch-insert")
	public ResponseEntity<?> uploadInPatientThruExcel(@RequestBody MultipartFile file) {
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getName());
		try {
			inPatientService.uploadInPatientThruExcel(file);
			return ResponseEntity.ok("Records inserted in DB using Sprig Batch"); 
		} catch (IOException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	@PostMapping("/api/outPatient/batch-insert")
	public ResponseEntity<?> uploadOutPatientThruExcel(@RequestBody MultipartFile file) {
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getName());
		try {
			outPatientService.uploadOutPatientThruExcel(file);
			return ResponseEntity.ok("Records inserted in DB using Sprig Batch"); 
		} catch (IOException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	@PostMapping("/api/doctor/batch-insert")
	public ResponseEntity<?> uploadDoctorPatientThruExcel(@RequestBody MultipartFile file) {
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getName());
		try {
			doctorService.uploadDoctorThruExcel(file);
			return ResponseEntity.ok("Records inserted in DB using Sprig Batch"); 
		} catch (IOException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping("/api/inpatient/all")
	public Page<InPatient> getAllInpatient(
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "5000") int size) {
		Pageable pageable =  PageRequest.of(page, size);
		logger.info("Fetching all inpatient using pageable...");
		if(size==5000)
			logger.warn("Fetching inpatient without limit ");
		return inPatientService.getAllInpatient(pageable);
	}
	
	
	@DeleteMapping("/api/inpatient/delete/{id}")
	public ResponseEntity<?> deleteInPatietById(@PathVariable int id, 
			ResponseMessageDto dto) 
					throws ResourceNotFoundException {
		logger.info("deleting inpatient by id");
		inPatientService.validate(id);
		inPatientService.deleteInPatientById(id);
		dto.setMsg("inpatient Deleted");
		logger.info("inpatient deleted with ID: " + id);
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/api/outpatient/all")
	public Page<InPatient> getAllOutpatient(
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "5000") int size) {
		Pageable pageable =  PageRequest.of(page, size);
		logger.info("Fetching all inpatient using pageable...");
		if(size==5000)
			logger.warn("Fetching inpatient without limit ");
		return inPatientService.getAllInpatient(pageable);
	}
	
	@DeleteMapping("/api/outpatient/delete/{id}")
	public ResponseEntity<?> deleteOutPatientById(@PathVariable int id, 
			ResponseMessageDto dto) 
					throws ResourceNotFoundException {
		logger.info("deleting outpatient by id");
		outPatientService.validate(id);
		outPatientService.deleteOutPatientById(id);
		dto.setMsg("outpatient Deleted");
		logger.info("outpatient deleted with ID: " + id);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/api/doctor/delete/{id}")
	public ResponseEntity<?> deleteDoctorById(@PathVariable int id, 
			ResponseMessageDto dto) 
					throws ResourceNotFoundException {
		logger.info("deleting doctor by id");
		doctorService.validate(id);
		doctorService.deleteDoctorById(id);
		dto.setMsg("doctor Deleted");
		logger.info("doctor deleted with ID: " + id);
		return ResponseEntity.ok(dto);
	}
	@GetMapping("/auth/userDetails")
	public ResponseEntity<?>getUserDetails(Principal principal){
		String loggedInUsername = principal.getName();
		User user  = (User)userSecurityService.loadUserByUsername(loggedInUsername);
		String role=user.getRole().toString();
		switch(role) {
		case "DOCTOR":
			return ResponseEntity.ok(doctorService.getDoctorDetails(user.getId()));
		case "EXECUTIVE":
			return ResponseEntity.ok(executiveService.getExecutiveDetails(user.getId()));
		case "ADMIN":
			return ResponseEntity.ok(adminService.getAdminByUserId(user.getId()));
		case "IN_PATIENT":
			return ResponseEntity.ok(patientService.getPatientByUserId(user.getId()));
		}
		return ResponseEntity.badRequest().body("NOT FOUND");
		
	}
	

}
