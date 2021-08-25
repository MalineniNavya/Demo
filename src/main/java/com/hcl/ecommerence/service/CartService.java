package com.hcl.ecommerence.service;

import java.util.List;

import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;


public interface CartService {
	
	public String addToCart(int userId,int productId) throws CartSizeExceedException;
	
	
	public List<ProductRequestDto> displayCart(int userId) throws CartSizeExceedException;

}
