package com.security.service;

import org.springframework.stereotype.Service;

@Service
public class UiService {

	public String welcome() {
		return "welcome this endpoint is shown to logined users";
	}

	public String adminMsg() {
		return "only for admins";
	}

	public String userMsg() {
		return "only for users";
	}

	public String test() {
		return "endpoint is not secured can be viewed by anyone";
	}

}
