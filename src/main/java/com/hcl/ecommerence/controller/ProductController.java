package com.hcl.ecommerence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.ProductAlreadyExistException;
import com.hcl.ecommerence.service.ProductService;


@RestController
@RequestMapping("/api")

public class ProductController {
	@Autowired
	ProductService prodctService;
	
	@GetMapping("product/{productName}")
	public ResponseEntity<List<ProductRequestDto>> searchItem( @PathVariable String productName) throws ProductAlreadyExistException{
		return new ResponseEntity<List<ProductRequestDto>>(prodctService.searchProduct(productName),HttpStatus.OK);
	}

}
