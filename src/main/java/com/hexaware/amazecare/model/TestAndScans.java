package com.hexaware.amazecare.model;

import java.time.LocalDate;

import com.hexaware.amazecare.enums.ScanTestType;
import com.hexaware.amazecare.enums.TestScanStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TestAndScans {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ScanTestType type;
	@Enumerated(EnumType.STRING)
	private TestScanStatus status;
	private LocalDate testOrScanPrescibedon;
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private Patient patient;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getTestOrScanPrescibedon() {
		return testOrScanPrescibedon;
	}
	public void setTestOrScanPrescibedon(LocalDate testOrScanPrescibedon) {
		this.testOrScanPrescibedon = testOrScanPrescibedon;
	}
	public ScanTestType getType() {
		return type;
	}
	public void setType(ScanTestType type) {
		this.type = type;
	}
	public TestScanStatus getStatus() {
		return status;
	}
	public void setStatus(TestScanStatus status) {
		this.status = status;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "TestAndScans [id=" + id + ", type=" + type + ", status=" + status + ", testOrScanPrescibedon="
				+ testOrScanPrescibedon + ", description=" + description + ", doctor=" + doctor + ", patient=" + patient
				+ "]";
	}
	
	

}
