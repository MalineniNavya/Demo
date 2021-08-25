package com.hcl.ecommerence.service;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;

import com.hcl.ecommerence.dto.UserRequestDto;
import com.hcl.ecommerence.exception.InvalidCredentialsException;
import com.hcl.ecommerence.model.User;


public interface UserService {
	
	public  ResponseEntity<User> addUser(UserRequestDto userRequestDto) throws InvalidCredentialsException ;
	
	public ResponseEntity<Boolean> deleteUser(int id) throws InvalidCredentialsException;
	
	public  ResponseEntity<User> updateUser(int id,UserRequestDto userRequestDto) throws InvalidCredentialsException;
	
	public ResponseEntity<Optional<User>> getUserById(int userId) throws InvalidCredentialsException;

	public ResponseEntity<List<User>> displayAllUser();
	
	public ResponseEntity<String> authenticate(String username, String password) throws InvalidCredentialsException ;

}
