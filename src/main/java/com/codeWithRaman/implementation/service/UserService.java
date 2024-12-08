package com.codeWithRaman.implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeWithRaman.implementation.model.User;
import com.codeWithRaman.implementation.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void registerUser(String username, String password, String email) {

		if (userRepository.findByUsername(username) != null) {
			throw new IllegalArgumentException("Username is already taken.");
		}

		if (userRepository.findByEmail(email) != null) {
			throw new IllegalArgumentException("Email is already taken.");
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setRole("USER");
		userRepository.save(user);

		System.out.println("\nUser created:");
		System.out.println("Username: " + user.getUsername());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Role: " + user.getRole());
		System.out.println("Created at: " + user.getCreated_at());
	}

	public User loginUser(String username, String password) {
		// ค้นหาผู้ใช้ในฐานข้อมูล
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			return user; // คืนค่าผู้ใช้หากรหัสผ่านถูกต้อง
		}
		return null; // คืนค่า null หาก login ไม่สำเร็จ
	}
}