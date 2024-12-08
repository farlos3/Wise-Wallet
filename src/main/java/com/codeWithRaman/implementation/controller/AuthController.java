package com.codeWithRaman.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import com.codeWithRaman.implementation.service.UserService;
import com.codeWithRaman.implementation.model.User;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String confirmPassword,
			@RequestParam String email,
			Model model) {

		if (!password.equals(confirmPassword)) {
			model.addAttribute("errorMessage", "Passwords do not match.");
			return "register";
		}

		try {
			userService.registerUser(username, password, email);
			return "redirect:/login";
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register";
		}
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(
			@RequestParam String username,
			@RequestParam String password,
			Model model,
			HttpSession session) {
		User user = userService.loginUser(username, password);
		System.out.println("User attempting to log in: " + username);
		if (user != null) {
			session.setAttribute("username", user.getUsername());
			System.out.println("Login successful for user: " + user.getUsername());
			return "redirect:/welcome"; // Login สำเร็จ
		} else {
			model.addAttribute("errorMessage", "Invalid username or password."); // ข้อความแจ้งเตือน
			System.out.println("Login failed for user: " + username);
			return "login"; // กลับไปที่หน้า login
		}
	}

	@GetMapping("/welcome")
	public String welcomePage(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		System.out.println("Username from session: " + username);
		if (username == null) {
			return "redirect:/login";
		}
		model.addAttribute("username", username);
		return "welcome";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
