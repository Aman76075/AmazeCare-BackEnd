package com.hexaware.amazecare.dto;

import org.springframework.stereotype.Component;

@Component
public class UpdateDetailsDto {
	private String contact;
	private String email;
	@Override
	public String toString() {
		return "UpdateDetailsDto [contact=" + contact + ", email=" + email + "]";
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
