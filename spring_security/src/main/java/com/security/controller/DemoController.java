package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.dto.AuthRequest;
import com.security.dto.DemoRequest;
import com.security.service.DemoService;

@RestController
@RequestMapping("api")
public class DemoController {

	@Autowired
	private DemoService service;

	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody DemoRequest req) {
		return service.addUser(req);
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		return service.getAllUsers();
	}

	@PostMapping("/auth")
	public ResponseEntity<?> authUser(@RequestBody AuthRequest request) {
		return service.authUser(request);
	}

}
