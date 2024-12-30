package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Admin;
import com.hexaware.amazecare.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	public Admin getAdminByUserId(int id) {
		return adminRepository.getAdminById(id);
	}
	
	

}
