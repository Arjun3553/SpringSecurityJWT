package com.security.dto;

public class DemoRequest {

	private String name;
	private String password;
	private String email;
	private String roles;

	public DemoRequest() {

	}

	public DemoRequest(String name, String password, String email, String roles) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "DemoRequest [name=" + name + ", password=" + password + ", email=" + email + ", roles=" + roles + "]";
	}

}
