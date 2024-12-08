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

	public void registerUser(String username, String password, String email) { // เพิ่ม email เป็นพารามิเตอร์
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); // เข้ารหัส password
		user.setEmail(email); // ตั้งค่า email
		user.setRole("USER"); // ตั้งค่า role เป็น USER
		userRepository.save(user); // บันทึก user ลงฐานข้อมูล

		// พิมพ์ข้อมูลทั้งหมดของ user
		System.out.println("\nUser created:");
		System.out.println("Username: " + user.getUsername());
		System.out.println("Email: " + user.getEmail());
		System.out.println("Role: " + user.getRole());
		System.out.println("Created at: " + user.getCreated_at());
	}
}
