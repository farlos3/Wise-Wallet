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

    public void registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setQuota(3);
        user.setTotalBalance(0);
        user.setTotalExpenses(0);
        user.setRole("USER");
        userRepository.save(user);

        System.out.println("\nUser created:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());
        System.out.println("Quota: " + user.getQuota());
        System.out.println("Total Balance: " + user.getTotalBalance());
        System.out.println("Total Expenses: " + user.getTotalExpenses());
        System.out.println("Created at: " + user.getCreated_at() + "\n");
    }

    public boolean authenticate(String username, String password) {
        // ค้นหา User จากฐานข้อมูล
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // ตรวจสอบ Password
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false; // หากไม่พบ User หรือ Password ไม่ตรง
    }
}