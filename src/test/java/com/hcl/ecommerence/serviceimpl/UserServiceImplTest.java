package com.hcl.ecommerence.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
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

import com.hcl.ecommerence.dao.UserDao;
import com.hcl.ecommerence.dto.UserRequestDto;
import com.hcl.ecommerence.exception.InvalidCredentialsException;
import com.hcl.ecommerence.model.User;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	UserDao userDao;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
    static UserRequestDto userRequestDto;
    static User user;
      
   
   
   @BeforeAll
   public static void setUp() { 
	   userRequestDto = new UserRequestDto();
	   
	   userRequestDto.setUserId(1);
	   userRequestDto.setUserName("Navya");
	   userRequestDto.setPassword("abcd");
	   userRequestDto.setMobileNo(+919875676786L);
	   
	   user = new User(); 
	   user.setUserId(1);
	   user.setUserName("Navya");
	   user.setPassword("abcd");
	   user.setMobileNo(+919875676786L);
		  

		    
 }
   
	 
    @Test
    @DisplayName("authentication : positive scenario") 
    public void authenticationTest() throws InvalidCredentialsException  {
    	
    //context
     when(userDao.findByUserNameAndPassword("Navya", "abcd")).thenReturn(user);
     
    //event 
     ResponseEntity<String> result = userServiceImpl.authenticate("Navya", "abcd");
     
     //outcome 
     assertEquals("Login success", result.getBody());
     assertEquals(HttpStatus.OK, result.getStatusCode()); 
     
    }
     
    @Test
    @DisplayName("authentication : negative scenario") 
   public void authenticationTest1() { 
    	// context
       when(userDao.findByUserNameAndPassword("anu", "1234")).thenReturn(null);
     
   //  event and outcome 
       assertThrows(InvalidCredentialsException.class, () ->userServiceImpl.authenticate("anu", "1234"));
    }    
 
}
    

	











