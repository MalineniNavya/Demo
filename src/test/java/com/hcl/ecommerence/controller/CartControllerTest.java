package com.hcl.ecommerence.controller;


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
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;


import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;
import com.hcl.ecommerence.serviceimpl.CartServiceImpl;
import com.hcl.ecommerence.serviceimpl.CartServiceImplTest;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {
	
	
	@Mock
	CartServiceImpl cartServiceImpl;
	
	@InjectMocks
	CartController cartController;
	
	static Cart cart;
	static User user;
	static Products product;
	static ProductRequestDto productRequestDto;
	
	@BeforeAll
	public static void setUp() {
		  
	   user = new User(); 
	   user.setUserId(1);
	   user.setUserName("Navya");
	   user.setPassword("abcd");
	   user.setMobileNo(+919875676786L);
		
		product = new Products();
		product.setProductId(1);
		product.setProductName("Refrigerator");
		product.setProductPrice(15000);
		
		cart=new Cart();
		cart.setCartId(1);
		cart.setProduct(product);
		cart.setUser(user);
		
	}
	
	@Test
	@DisplayName("AddCart :Positive Scenario")
	public void testAddToCart() throws CartSizeExceedException {
		when(cartServiceImpl.addToCart(1,1)).thenReturn("Product added to cart successfully");
		ResponseEntity<String> result= cartController.addToCart(1, 1);
		assertEquals("Product added to cart successfully",result.getBody());

	}
	
	@Test
	@DisplayName("AddcartUser")
	public void testAddToCart2() throws CartSizeExceedException {
		when(cartServiceImpl.addToCart(2,1)).thenReturn("User Id does not exists");
		ResponseEntity<String> result= cartController.addToCart(2, 1);
		assertEquals("User Id does not exists",result.getBody());
		
	}
	
	@Test
	@DisplayName("AddCartProdct")
	public void testAddToCart3() throws CartSizeExceedException {
		when(cartServiceImpl.addToCart(1,2)).thenReturn("Product does not exists");
		ResponseEntity<String> result= cartController.addToCart(1, 2);
		assertEquals("Product does not exists",result.getBody());
		
	}
	
	
	@Test
	@DisplayName(value="displayCart")
	public void testDisplay() throws CartSizeExceedException {
		List<ProductRequestDto> cartList = new ArrayList<ProductRequestDto>();
		productRequestDto = new ProductRequestDto();
		List<Cart> cartList1=new ArrayList<Cart>();
		BeanUtils.copyProperties(cartList1, productRequestDto);
		cartList.add(productRequestDto);
		
		when(cartServiceImpl.displayCart(1)).thenReturn(cartList);
		ResponseEntity<List<ProductRequestDto>> result= cartController.displayCart(1);
		assertEquals(cartList,result.getBody());
		
	}
	
	



}



