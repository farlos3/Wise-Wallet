package com.codeWithRaman.implementation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeWithRaman.implementation.service.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam String username,
			@RequestParam String password,
			@RequestParam String confirmPassword,
			@RequestParam String email,
			Model model) {
		if (!password.equals(confirmPassword)) {
			model.addAttribute("errorMessage", "Passwords do not match.");
			return "register"; // ใช้ Model แทนการ redirect
		}
		userService.registerUser(username, password, email);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("/game")
	public String game() {
		return "redirect:/WebGLBuilds/index.html"; // Redirect ไปยังไฟล์ใน static
	}
}