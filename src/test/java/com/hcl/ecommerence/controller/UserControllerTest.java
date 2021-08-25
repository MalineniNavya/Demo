package com.hcl.ecommerence.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerence.dto.Credentails;
import com.hcl.ecommerence.dto.UserRequestDto;
import com.hcl.ecommerence.exception.InvalidCredentialsException;
import com.hcl.ecommerence.serviceimpl.UserServiceImpl;



@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	
	@Mock
	UserServiceImpl userServiceImpl;	
	
	@InjectMocks
	UserController userController;    
	
	static UserRequestDto userRequestDto;
	
	static Credentails credentials;   
	   
	 @BeforeAll
	 public static void setUp() { 
		userRequestDto = new UserRequestDto();
		   
		userRequestDto.setUserId(1);
		userRequestDto.setUserName("Navya");
		userRequestDto.setPassword("abcd");
		userRequestDto.setMobileNo(+919875676786L);
		   
		credentials = new Credentails();
		credentials.setUsername("Navya");
		credentials.setPassword("abcd");
		

 }
	 
	 
	 @Test
	 @DisplayName("Login Function: Positive Scenario")
	 public void loginTest() throws InvalidCredentialsException{
		 
		//context			
		 when(userServiceImpl.authenticate("Navya", "abcd")).thenReturn(new ResponseEntity<>("Login success", HttpStatus.OK));
			
		//event
		ResponseEntity<String> result = userController.login(credentials);
			
		//outcome			
		assertEquals("Login success", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		}
		
	 
	 
	@Test
	@DisplayName("Login Function: Negative Scenario")
	public void loginTest2() throws InvalidCredentialsException {
		
		
		
	//context
	when(userServiceImpl.authenticate("Navya", "abcd")).thenThrow(InvalidCredentialsException.class);
			
	//event
	//outcome
	assertThrows(InvalidCredentialsException.class, ()->userController.login(credentials));		
	}
	
		
	   



}
