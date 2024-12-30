package com.hexaware.amazecare.model;

import com.hexaware.amazecare.enums.PatientType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private PatientType patientType;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PatientType getPatientType() {
		return patientType;
	}

	public void setPatientType(PatientType patientType) {
		this.patientType = patientType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", patientType=" + patientType + ", user=" + user + "]";
	}

	public Patient(int id, PatientType patientType, User user) {
		super();
		this.id = id;
		this.patientType = patientType;
		this.user = user;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
