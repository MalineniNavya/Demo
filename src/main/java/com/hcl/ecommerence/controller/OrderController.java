package com.hcl.ecommerence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerence.dto.OrderRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("api/items/{userId}")
	public ResponseEntity<String> orderItem(@PathVariable int userId) throws CartSizeExceedException{
		return new ResponseEntity<String>(orderService.orderItem(userId),HttpStatus.OK);
	}
	
	@GetMapping("/api/items/{userId}")
	public ResponseEntity<List<OrderRequestDto>> displayOrders(@PathVariable int userId) throws CartSizeExceedException{
		return new ResponseEntity<List<OrderRequestDto>>(orderService.displayOrders(userId),HttpStatus.OK);
	}


}
