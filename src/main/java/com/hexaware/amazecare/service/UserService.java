package com.hexaware.amazecare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.dto.UserCountStatsDto;
import com.hexaware.amazecare.enums.Role;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@Autowired
	private UserCountStatsDto userCountStatDto;

	public User insert(User user) {
		return userRepository.save(user);
	}

	public User signup(User user) throws InvalidUsernameException {
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if (optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}

		// encrypt the password
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);

		return userRepository.save(user);
	}

	public UserCountStatsDto getUserStats() {
		List<User> list = userRepository.findAll();
		Map<Role, List<User>> map = list.parallelStream().collect(Collectors.groupingBy(e -> e.getRole()));
		Set<Role> listRoles = map.keySet();
		List<Integer> listData = new ArrayList<>();
		for (Role role : map.keySet()) {
			int num = map.get(role).size();
			listData.add(num);
		}
		userCountStatDto.setLabels(listRoles);
		userCountStatDto.setData(listData);
		return userCountStatDto;
	}

}
