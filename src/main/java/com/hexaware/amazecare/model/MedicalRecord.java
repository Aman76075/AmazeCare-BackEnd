package com.hexaware.amazecare.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate generationDate;
	private String treatmentPlan;
	private String prescription;
	
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
	public LocalDate getGenerationDate() {
		return generationDate;
	}
	public void setGenerationDate(LocalDate generationDate) {
		this.generationDate = generationDate;
	}
	public String getTreatmentPlan() {
		return treatmentPlan;
	}
	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
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
		return "MedicalRecord [id=" + id + ", generationDate=" + generationDate + ", treatmentPlan=" + treatmentPlan
				+ ", prescription=" + prescription + ", doctor=" + doctor + ", patient=" + patient + "]";
	}
	public MedicalRecord(int id, LocalDate generationDate, String treatmentPlan, String prescription, Doctor doctor,
			Patient patient) {
		super();
		this.id = id;
		this.generationDate = generationDate;
		this.treatmentPlan = treatmentPlan;
		this.prescription = prescription;
		this.doctor = doctor;
		this.patient = patient;
	}
	public MedicalRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
