package com.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.service.UiService;

@RestController
@RequestMapping("api")
public class UiController {

	private UiService service;

	public UiController(UiService service) {
		super();
		this.service = service;
	}

	@GetMapping("/welcome")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String welcome() {
		return service.welcome();
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminMsg() {
		return service.adminMsg();
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userMsg() {
		return service.userMsg();

	}

	@GetMapping("/test")
	public String test() {
		return service.test();
	}
}
