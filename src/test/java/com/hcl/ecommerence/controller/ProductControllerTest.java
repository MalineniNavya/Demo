package com.hcl.ecommerence.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.ProductAlreadyExistException;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.serviceimpl.ProductServiceImpl;
import com.hcl.ecommerence.serviceimpl.ProductServiceImplTest;




@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@Mock
	ProductServiceImpl productServiceImpl;
	
	@InjectMocks
	ProductController productController;
	
	static Products products;
	static ProductRequestDto productRequestDto;
	@BeforeAll
	public static void setUp() {
		products = new Products();
		products.setProductId(1);
		products.setProductName("Refrigerator");
	    products.setProductPrice(15000);

		
	    productRequestDto = new ProductRequestDto();
	    productRequestDto.setProductId(1);
	    productRequestDto.setProductName("Refrigerator");
		productRequestDto.setProductPrice(15000);

	}
	
	@Test
	@DisplayName(value="SearchProducts:Positive Scenario")
	public void testSearch() throws ProductAlreadyExistException {
		List<ProductRequestDto> productList = new ArrayList<ProductRequestDto>();
		productList.add(productRequestDto);
		when(productServiceImpl.searchProduct("Refrigerator")).thenReturn(productList);
		ResponseEntity<List<ProductRequestDto>> list = productController.searchItem("Refrigerator");
		assertEquals(productList,list.getBody());
	}
	
	@Test
	@DisplayName(value="SearchProducts:Negative Scenario")
	public void testSearch1() throws ProductAlreadyExistException {
		productRequestDto=new ProductRequestDto();
		List<ProductRequestDto> productList = new ArrayList<ProductRequestDto>();
		when(productServiceImpl.searchProduct("tv")).thenReturn(productList);
		ResponseEntity<List<ProductRequestDto>> list = productController.searchItem("tv");
		assertEquals(productList,list.getBody());
		
	}
	

}

