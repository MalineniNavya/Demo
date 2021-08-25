package com.hcl.ecommerence.dto;

import javax.validation.constraints.NotEmpty;

public class Credentails {
	
	@NotEmpty(message = "Username should not be empty")
	private String userName;
	
	@NotEmpty(message = "password should not be empty")
	private String password;
	
	
	public Credentails() {
		
	}
	
	public Credentails( String userName,String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

public String getUsername() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}


