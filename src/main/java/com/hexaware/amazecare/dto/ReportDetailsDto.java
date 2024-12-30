package com.hexaware.amazecare.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ReportDetailsDto {
	private String name;
	private int age;
	private String gender;
	private String patient_type;
	private String doctorName;
	private String labOperatorName;
	private String department;
	private LocalDate generatedOn;
	private String scanTestType;
	private String description;
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getLabOperatorName() {
		return labOperatorName;
	}
	public void setLabOperatorName(String labOperatorName) {
		this.labOperatorName = labOperatorName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public LocalDate getGeneratedOn() {
		return generatedOn;
	}
	public void setGeneratedOn(LocalDate generatedOn) {
		this.generatedOn = generatedOn;
	}
	public String getScanTestType() {
		return scanTestType;
	}
	public void setScanTestType(String scanTestType) {
		this.scanTestType = scanTestType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ReportDetailsDto [name=" + name + ", age=" + age + ", gender=" + gender + ", patient_type="
				+ patient_type + ", doctorName=" + doctorName + ", labOperatorName=" + labOperatorName + ", department="
				+ department + ", generatedOn=" + generatedOn + ", scanTestType=" + scanTestType + ", description="
				+ description + "]";
	}
	
}
