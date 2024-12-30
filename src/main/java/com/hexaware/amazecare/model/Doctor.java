package com.hexaware.amazecare.model;

import java.time.LocalDate;


import com.hexaware.amazecare.enums.Department;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String contact;
	private int experience;
	private LocalDate joiningDate;
	
	@Enumerated(EnumType.STRING)
	private Department department;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + ", experience="
				+ experience + ", joiningDate=" + joiningDate + ", department=" + department + ", user=" + user + "]";
	}

	public Doctor(int id, String name, String email, String contact, int experience, LocalDate joiningDate,
			Department department, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.experience = experience;
		this.joiningDate = joiningDate;
		this.department = department;
		this.user = user;
	}

	public Doctor() {
		super();
		
	}
	
	

}
