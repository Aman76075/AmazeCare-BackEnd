package com.hexaware.amazecare.model;


import java.time.LocalDate;

import com.hexaware.amazecare.enums.TimeSlot;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DoctorSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate scheduleDate;
	
	@Enumerated(EnumType.STRING)
	private TimeSlot timeSlot;
	private int totalSlots;
	@ManyToOne
	private Doctor doctor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
	public int getTotalSlots() {
		return totalSlots;
	}
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	@Override
	public String toString() {
		return "DoctorSchedule [id=" + id + ", scheduleDate=" + scheduleDate + ", timeSlot=" + timeSlot
				+ ", totalSlots=" + totalSlots + ", doctor=" + doctor + "]";
	}
	
	
	

}
