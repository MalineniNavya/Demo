package com.hcl.ecommerence.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.ecommerence.dao.UserDao;
import com.hcl.ecommerence.dto.UserRequestDto;
import com.hcl.ecommerence.exception.InvalidCredentialsException;
import com.hcl.ecommerence.model.User;
import com.hcl.ecommerence.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	User user = new User();

	@Override
	public ResponseEntity<User> addUser(UserRequestDto userRequestDto) throws InvalidCredentialsException {
			BeanUtils.copyProperties(userRequestDto,user);
			if (!userDao.findById(userRequestDto.getUserId()).isPresent()) 
				throw new InvalidCredentialsException("User already exist");	
			
			return new ResponseEntity<User>( userDao.save(user),HttpStatus.OK);

}

	@Override
	public ResponseEntity<Boolean> deleteUser(int id) throws InvalidCredentialsException {
	
		if(!userDao.findById(id).isPresent()) 
			throw new InvalidCredentialsException("UserId doesn't exists");
		userDao.deleteById(id);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

	

	@Override
	public ResponseEntity<User> updateUser(int id, UserRequestDto userRequestDto) throws InvalidCredentialsException {

		Optional<User> findById =userDao.findById(userRequestDto.getUserId());
		BeanUtils.copyProperties(userRequestDto,user);

	   if (!findById.isPresent()) 
			throw new InvalidCredentialsException("UserId doesn't exists");
	   return new ResponseEntity<User>( userDao.save(user),HttpStatus.OK);


}


	@Override
	public ResponseEntity<String> authenticate(String username, String password) throws InvalidCredentialsException {
		User user = userDao.findByUserNameAndPassword(username, password);
		if (user != null)
			return new ResponseEntity<>("Login success", HttpStatus.OK);
		throw new InvalidCredentialsException("Invalid Credentials..!!Please Verify your Credentials and Try Again.");
	}


	@Override
	public ResponseEntity<Optional<User>> getUserById(int userId) throws InvalidCredentialsException {
		if(!userDao.findById(userId).isPresent()) 
			throw new InvalidCredentialsException("UserId doesn't exists");
		return new ResponseEntity<Optional<User>>( userDao.findById(userId),HttpStatus.OK);


	}

	@Override
	public ResponseEntity<List<User>> displayAllUser() {
			return new ResponseEntity<List<User>>(userDao.findAll(), HttpStatus.OK);
 }


}

	



