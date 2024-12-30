package com.hexaware.amazecare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.dto.UserCountStatsDto;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.model.Admin;
import com.hexaware.amazecare.model.Executive;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.AdminService;
import com.hexaware.amazecare.service.ExecutiveService;
import com.hexaware.amazecare.service.UserService;
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/api/addAdmin")
	public Admin onBoardAdmin(@RequestBody Admin admin) throws InvalidUsernameException{
	User user=admin.getUser();
	user=userService.signup(user);
	admin.setUser(user);
	return adminService.addAdmin(admin);
	}
	@GetMapping("/api/getAllExecutives")
	public List<Executive> getAllExecutives(){
		return executiveService.getAllExecutives();
	}
	@GetMapping("/api/users-stat")
	public UserCountStatsDto getUserStats() {
		return userService.getUserStats();
	}

}
