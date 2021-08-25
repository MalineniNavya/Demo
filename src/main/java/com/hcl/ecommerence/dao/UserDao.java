package com.hcl.ecommerence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerence.model.User;



@Repository
public interface UserDao extends JpaRepository<User,Integer> {
	
	public User findByUserNameAndPassword(String userName,String password);
	
	public User findByUserId(int userId);



}
