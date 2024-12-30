
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

import com.hexaware.amazecare.dto.AppointmentDto;
import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.dto.UpdateDetailsDto;
import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.enums.ScanTestType;
import com.hexaware.amazecare.enums.TestScanStatus;
import com.hexaware.amazecare.enums.TimeSlot;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Appointment;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.DoctorSchedule;
import com.hexaware.amazecare.model.MedicalRecord;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.AppointmentService;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.MedicalRecordService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.TestAndScansService;
import com.hexaware.amazecare.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
	private TestAndScansService testAndScansService;
	Logger logger = LoggerFactory.getLogger(DoctorController.class);
	
	@PostMapping("/doctor/register")
	public Doctor registerDoctor(@RequestBody Doctor doctor) {
		User user=new User();
		user.setUsername(doctor.getUser().getUsername());
		user.setPassword(doctor.getUser().getPassword());
		user.setRole(doctor.getUser().getRole());
		user=userService.insert(user);
		doctor.setUser(user);
		doctor.setJoiningDate(LocalDate.now());
		
		return doctorService.insert(doctor);
	}
	@GetMapping("/doctor/viewAppointment/{did}")
	public List<Appointment> viewAllAppointment(@PathVariable int did){
		return doctorService.getAllAppointments(did);
	}
	@PostMapping("/doctor/generateMedicalRecord/{did}/{pid}")
	public ResponseEntity<?> generateRecord(@PathVariable int did,@PathVariable int pid,@RequestBody MedicalRecord medicalRecord,ResponseMessageDto dto,Principal principal) {
		logger.info("API Acccessed by "+principal.getName());
		Doctor doctor=null;
		Patient patient=null;
	    try {
	    	doctor=doctorService.validate(did);
	    	patient=patientService.validate(pid);
	    	
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    medicalRecord.setPatient(patient);
	    medicalRecord.setDoctor(doctor);
	    medicalRecord.setGenerationDate(LocalDate.now());
	    medicalRecord=medicalRecordService.insert(medicalRecord);
	    return ResponseEntity.ok(medicalRecord);
	    
	}
	@PostMapping("doctor/testnscans/{did}/{pid}")
	public ResponseEntity<?>getTestnScans(@PathVariable int did,@PathVariable int pid,@RequestBody TestAndScans testAndScans,ResponseMessageDto dto,Principal principal){
		logger.info("API Acccessed by "+principal.getName());
		Doctor doctor=null;
		Patient patient=null;
	    try {
	    	doctor=doctorService.validate(did);
	    	patient=patientService.validate(pid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    testAndScans.setPatient(patient);
	    testAndScans.setDoctor(doctor);
	    testAndScans.setStatus(TestScanStatus.PENDING);
	    testAndScans.setTestOrScanPrescibedon(LocalDate.now());
	    testAndScans=testAndScansService.insert(testAndScans);
	    return ResponseEntity.ok(testAndScans);
	}
	@GetMapping("/doctor/appointment/getAll/{did}")
	public List<AppointmentDto> fetchAllAppointments(@PathVariable int did,Principal principal){
		logger.info("API Acccessed by "+principal.getName());
		List<AppointmentDto> list=doctorService.fetchAllAppointments(did);
		return list;
	}
	@GetMapping("/doctor/getAllTestType")
	public List<ScanTestType> getAllTestType(){
		return Arrays.asList(ScanTestType.values());
	}
	@PostMapping("/doctor/setSchedule/{did}")
	public DoctorSchedule setDoctorSchedule(@RequestBody DoctorSchedule doctorSchedule,@PathVariable int did) throws ResourceNotFoundException {
		Doctor doctor=doctorService.validate(did);
		doctorSchedule.setDoctor(doctor);
		return doctorService.setSchedule(doctorSchedule);
		
	}
	@GetMapping("/doctor/getAllTimeSlots")
	public List<TimeSlot> getAllTimeSlots(){
		return Arrays.asList(TimeSlot.values());
	}
	@GetMapping("/doctor/getDetails/{did}")
	public Doctor getDoctorDetails(@PathVariable int did) {
		return doctorService.getDoctorDetailsByDoctorId(did);
	}
	@PostMapping("/doctor/updateDoctor/{did}")
	public Doctor updateDoctor(@RequestBody UpdateDetailsDto dto,@PathVariable int did) {
		Doctor doc=doctorService.getDoctorDetailsByDoctorId(did);
		doc.setContact(dto.getContact());
		doc.setEmail(dto.getEmail());
		return doctorService.insert(doc);
	}
	@GetMapping("/medicalrecord/{pid}")
	public Page<MedicalRecord> getMedicalRecord(@PathVariable int pid,
			@RequestParam(required = false, defaultValue = "0") String page,
			@RequestParam(required = false, defaultValue = "1000000") String size) throws Exception{
		Pageable pageable = null;

		try {
			pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		} catch (Exception e) {
			throw e;
		}
		Page<MedicalRecord> list =medicalRecordService.getAllMedicalRecordwithId(pid,pageable);
		return list; 
	}
	@GetMapping("/appointment/completed/{aid}")
	public Appointment markAsCompleted(@PathVariable int aid) {
		Appointment appoint=appointmentService.getAppointment(aid);
		System.out.println(appoint);
		appoint.setStatus(Appointment_Status.COMPLETED);
		return appointmentService.addAppointment(appoint);
	}
	@GetMapping("/getDoctorByDepartment/{dept}")
	public List<Doctor> getAllDoctorsByDepartment(@PathVariable String dept){
		return doctorService.getAllDoctorsByDepartment(dept);
	}

}
