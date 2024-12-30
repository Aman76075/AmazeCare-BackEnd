package com.hexaware.amazecare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.UpdateDetailsDto;
import com.hexaware.amazecare.model.InPatient;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.InPatientService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class InPatientController {
	@Autowired
	private InPatientService inPatientService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientService patientService;
	
	
	@PostMapping("/inpatient/register")
	public InPatient registerInPatient(@RequestBody InPatient inPatient) {
		User user=new User();
		user.setUsername(inPatient.getPatient().getUser().getUsername());
		user.setPassword(inPatient.getPatient().getUser().getPassword());
		user.setRole(inPatient.getPatient().getUser().getRole());
		user=userService.insert(user);
		
		Patient patient=new Patient();
		patient.setUser(user);
		patient.setPatientType(inPatient.getPatient().getPatientType());
		
		patient=patientService.insert(patient);
		
		inPatient.setPatient(patient);
		
		return inPatientService.insert(inPatient);	
	}
	@GetMapping("/inpatient/all")
	public List<InPatient> allInPatient(){
		return inPatientService.getall();
	}
	@GetMapping("/inpatient/getDetails/{pid}")
	public InPatient getInPatientDetails(@PathVariable int pid) {
		return inPatientService.getPatientDetilasByPid(pid);
	}
	@PostMapping("/inpatient/updateDetails/{pid}")
	public InPatient updatePatientDetails(@PathVariable int pid,@RequestBody UpdateDetailsDto dto) {
		InPatient inpatient=inPatientService.getPatientDetilas(pid);
		inpatient.setContact(dto.getContact());
		inpatient.setEmail(dto.getEmail());
		return inPatientService.insert(inpatient);
	}

}
