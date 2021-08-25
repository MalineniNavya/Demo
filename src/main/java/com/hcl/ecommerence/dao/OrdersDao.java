package com.hcl.ecommerence.dao;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.Orders;



@Repository
public interface OrdersDao extends JpaRepository<Orders,Integer> {
	
	@Query(value="select * from orders c where c.user_id=:userId",nativeQuery=true)
	   List<Orders> displayOrders(@Param("userId") int userId);
	
	public List<Orders> findOrdersByUserUserId(int userId);

	



}
