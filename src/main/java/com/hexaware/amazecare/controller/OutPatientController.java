package com.hexaware.amazecare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hexaware.amazecare.model.OutPatient;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.OutPatientService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class OutPatientController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private OutPatientService outPatientService;
	
	@PostMapping("/outpatient/register")
	public OutPatient registerInPatient(@RequestBody OutPatient outPatient) {
		User user=new User();
		user.setUsername(outPatient.getPatient().getUser().getUsername());
		user.setPassword(outPatient.getPatient().getUser().getPassword());
		user.setRole(outPatient.getPatient().getUser().getRole());
		user=userService.insert(user);
		
		Patient patient=new Patient();
		patient.setUser(user);
		patient.setPatientType(outPatient.getPatient().getPatientType());
		
		patient=patientService.insert(patient);
		
		outPatient.setPatient(patient);
		
		return outPatientService.insert(outPatient);	
	}
	@GetMapping("/outpatient/all")
	public List<OutPatient> allOutPatient(){
		return outPatientService.getall();
	}

}
