package com.hcl.ecommerence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hcl.ecommerence.dto.OrderRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Orders;



public interface OrderService {
	
	public String orderItem(int userId) throws CartSizeExceedException;
	
	public List<OrderRequestDto> displayOrders(int userId) throws CartSizeExceedException;
	
	public String orderItem1(int userId) throws CartSizeExceedException;

	

	/*
	 * public static List<OrderRequestDto> convertMyOrderToMyOrderDto(List<Orders>
	 * myOrderList) {​​​​​​​ List<OrderRequestDto> list=new ArrayList<>();
	 * myOrderList.forEach(e->{​​​​​​​ OrderRequestDto OrderRequestDto=new
	 * OrderRequestDto(); BeanUtils.copyProperties(e,OrderRequestDto);
	 * list.add(OrderRequestDto); }​​​​​​​);
	 * 
	 * return list; }​​​​​​​
	 */



}
