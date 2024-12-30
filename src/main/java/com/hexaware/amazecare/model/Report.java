package com.hexaware.amazecare.model;

import java.time.LocalDate;

import com.hexaware.amazecare.enums.ScanTestType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
	private ScanTestType scanTestType;
	private String description;
	private LocalDate generatedOn;
	
	@ManyToOne
	private Doctor doctor;
	
	@ManyToOne
	private Patient patient;
	
	@ManyToOne
	private LabOperator labOperator;
	
	@OneToOne
	private TestAndScans testAndScans;

	@Override
	public String toString() {
		return "Report [id=" + id + ", scanTestType=" + scanTestType + ", description=" + description + ", generatedOn="
				+ generatedOn + ", doctor=" + doctor + ", patient=" + patient + ", labOperator=" + labOperator
				+ ", testAndScans=" + testAndScans + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ScanTestType getScanTestType() {
		return scanTestType;
	}

	public void setScanTestType(ScanTestType scanTestType) {
		this.scanTestType = scanTestType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(LocalDate generatedOn) {
		this.generatedOn = generatedOn;
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

	public LabOperator getLabOperator() {
		return labOperator;
	}

	public void setLabOperator(LabOperator labOperator) {
		this.labOperator = labOperator;
	}

	public TestAndScans getTestAndScans() {
		return testAndScans;
	}

	public void setTestAndScans(TestAndScans testAndScans) {
		this.testAndScans = testAndScans;
	}
	

}
