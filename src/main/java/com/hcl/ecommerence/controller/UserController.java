package com.hcl.ecommerence.controller;

import java.io.InvalidClassException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerence.dto.Credentails;
import com.hcl.ecommerence.exception.InvalidCredentialsException;
import com.hcl.ecommerence.service.UserService;



@RestController
@RequestMapping("/api")

public class UserController {
	
	@Autowired
	UserService userService;

	
	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentails credentials) throws  InvalidCredentialsException {
		return userService.authenticate(credentials.getUsername(), credentials.getPassword());	
	}

}
