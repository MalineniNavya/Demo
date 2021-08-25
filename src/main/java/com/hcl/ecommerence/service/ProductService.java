package com.hcl.ecommerence.service;

import java.util.List;

import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.ProductAlreadyExistException;
import com.hcl.ecommerence.model.Products;

public interface ProductService {
	
	public List<ProductRequestDto> searchProduct(String productName) throws ProductAlreadyExistException ;
	public Products getProductById(int productId) ;


}
