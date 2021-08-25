package com.hcl.ecommerence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@NotEmpty(message = "UserName should not be empty")
	private String userName;
	
	@Size(max = 20,min=5,message = "Characters should be greater than 4 and less than 20")
	private String password;
	
	private long mobileNo;
	
	public User() {
		
	}

	public User(int userId,String userName, String password, long mobileNo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.mobileNo = mobileNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}
