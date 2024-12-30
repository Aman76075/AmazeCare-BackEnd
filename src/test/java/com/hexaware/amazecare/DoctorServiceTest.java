package com.hexaware.amazecare;
import static org.mockito.ArgumentMatchers.anyList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.amazecare.enums.Department;
import com.hexaware.amazecare.enums.PatientType;
import com.hexaware.amazecare.enums.Role;
import com.hexaware.amazecare.enums.ScanTestType;
import com.hexaware.amazecare.enums.TestScanStatus;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.MedicalRecord;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.MedicalRecordRepository;
import com.hexaware.amazecare.repository.TestAndScanRepository;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.MedicalRecordService;
import com.hexaware.amazecare.service.TestAndScansService;

@SpringBootTest
public class DoctorServiceTest {
	@InjectMocks
	private DoctorService doctorService;
	@InjectMocks
	private MedicalRecordService medicalRecordService;
	@InjectMocks
	private TestAndScansService testAndScansService;
	@Mock
	private TestAndScanRepository testAndScanRepository;
	@Mock
	private MedicalRecordRepository medicalRecordRepository;
	@Mock
	private DoctorRepository doctorRepository;
	
	private Doctor doctor1;
	private Doctor doctor2;
	private Patient patient;
	private MedicalRecord medicalRecord;
	private TestAndScans testAndScans;
	private List<Doctor>list;
	@BeforeEach
	public void initSetup() {
		LocalDate joiningDate = LocalDate.now();
	    Department dept1 = Department.CARDIOLOGY;
	    Department dept2 = Department.NUEROLOGY;

	    User user1 = new User();
	    user1.setUsername("rahul1");
	    user1.setPassword("1234");
	    user1.setRole(Role.DOCTOR);

	    User user2 = new User();
	    user2.setUsername("smith1");
	    user2.setPassword("5678");
	    user2.setRole(Role.DOCTOR);

	    doctor1 = new Doctor(1, "Rahul", "rahul@gmail.com", "678989", 2, joiningDate, dept1, user1);
	    doctor2 = new Doctor(2, "Smith", "smith@gmail.com", "123456", 3, joiningDate.minusDays(10), dept2, user2);

	    list = Arrays.asList(doctor1, doctor2);
	    User user=new User();
		user.setUsername("xyz");
		user.setPassword("sdf");
		user.setRole(Role.IN_PATIENT);
		
		patient=new Patient(1,PatientType.IN_PATIENT,user);
		medicalRecord=new MedicalRecord();
		medicalRecord.setId(1);
		medicalRecord.setDoctor(doctor1);
		medicalRecord.setPatient(patient);
		medicalRecord.setGenerationDate(LocalDate.now());
		medicalRecord.setPrescription("PARACEP");
		medicalRecord.setTreatmentPlan("Rest");
		
		testAndScans=new TestAndScans();
		testAndScans.setId(1);
		testAndScans.setDoctor(doctor1);
		testAndScans.setPatient(patient);
		testAndScans.setType(ScanTestType.CT_SCAN);
		testAndScans.setStatus(TestScanStatus.PENDING);
		testAndScans.setDescription("Brain CT SCAN");
		testAndScans.setTestOrScanPrescibedon(LocalDate.now());
		
		
	}
	@Test
	public void postCustomerTest() {
		//arrangement using mocking 
		when(doctorRepository.save(doctor1)).thenReturn(doctor1);
		
		//Act : Calling the actual method 
		Doctor newDoc =   doctorService.insert(doctor1); 
		
		//test and compare 
		assertNotNull(newDoc);
		//assertEquals(customer.getName(), newCustomer.getName());
		verify(doctorRepository, times(1)).save(doctor1);
	}
	@Test
	public void getAllDocs() {
		when(doctorRepository.findAll()).thenReturn(list);
		
		List<Doctor> newList=doctorService.getAllDoctors();
		assertNotNull(newList);
		assertEquals(2,newList.size());
		
		verify(doctorRepository,times(1)).findAll();
	}
	@Test
	public void generateMedicalRecord() {
		when(medicalRecordRepository.save(medicalRecord)).thenReturn(medicalRecord);
		
		MedicalRecord newMR=medicalRecordService.insert(medicalRecord);
		
		assertNotNull(newMR);
		
		verify(medicalRecordRepository,times(1)).save(medicalRecord);
	}
	@Test
	public void generateTest() {
		when(testAndScanRepository.save(testAndScans)).thenReturn(testAndScans);
		
		TestAndScans newTest=testAndScansService.insert(testAndScans);
		assertNotNull(newTest);
		verify(testAndScanRepository,times(1)).save(testAndScans);
	}
	
	
	
	

}
