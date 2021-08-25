package com.hcl.ecommerence.dto;



public class UserRequestDto {
	
	private int userId;
	
	private String userName;
	
	private String password;
	
	private long mobileNo;
	
	public UserRequestDto() {
		
	}

	public UserRequestDto(int userId,String userName, String password, long mobileNo) {
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



