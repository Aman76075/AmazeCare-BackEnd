package com.hexaware.amazecare.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
@Component
public class AppointmentDto {
	private int appointmentId;
	private int patientId;
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	private String name;
	private int age;
	private String gender;
	private String patient_type;
	private LocalDate date;
	private String timeSlot;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPatient_type() {
		return patient_type;
	}
	public void setPatient_type(String patient_type) {
		this.patient_type = patient_type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AppointmentDto [appointmentId=" + appointmentId + ", patientId=" + patientId + ", name=" + name
				+ ", age=" + age + ", gender=" + gender + ", patient_type=" + patient_type + ", date=" + date
				+ ", timeSlot=" + timeSlot + ", status=" + status + "]";
	}
	

}
