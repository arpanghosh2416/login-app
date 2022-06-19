package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("user-api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String test() {
		return "<h1>Server is running</h1>";
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		User requestedUser = userService.login(user);
		if (requestedUser != null) {
			return ResponseEntity.ok(user);
		}
		return new ResponseEntity<String>("<h1>No user found</h1>", HttpStatus.FORBIDDEN);
	}

}
