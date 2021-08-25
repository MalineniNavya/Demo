package com.hcl.ecommerence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.service.CartService;



@RestController
@RequestMapping("/api")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/cart/{userId}/{productId}")
	public ResponseEntity<String> addToCart(@PathVariable int userId,@PathVariable int productId) throws CartSizeExceedException{
		return new ResponseEntity<String>(cartService.addToCart(userId, productId),HttpStatus.OK);
	}
	
	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<ProductRequestDto>> displayCart(@PathVariable int userId) throws CartSizeExceedException{
		return new ResponseEntity<List<ProductRequestDto>>(cartService.displayCart(userId),HttpStatus.OK);
	}


}
