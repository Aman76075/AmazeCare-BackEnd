package com.hexaware.amazecare.model;

import java.time.LocalDate;

import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.enums.TimeSlot;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private LocalDate date;
	
	
    @Enumerated(EnumType.STRING)
    private Appointment_Status status;
    
    public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Enumerated(EnumType.STRING)
    private TimeSlot timeSlot;
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Doctor doctor;

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", status=" + status + ", timeSlot="
				+ timeSlot + ", patient=" + patient + ", doctor=" + doctor + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Appointment_Status getStatus() {
		return status;
	}

	public void setStatus(Appointment_Status status) {
		this.status = status;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
