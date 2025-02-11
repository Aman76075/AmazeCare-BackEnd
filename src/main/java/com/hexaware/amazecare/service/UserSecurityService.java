package com.hexaware.amazecare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = userRepository.findByUsername(username);
		if (optional.isEmpty())
			throw new UsernameNotFoundException("Invalid Username");
		User user = optional.get();
		return user;
	}

}
