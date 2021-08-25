package com.hcl.ecommerence.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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

import com.hcl.ecommerence.dto.OrderRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.CartItems;
import com.hcl.ecommerence.model.Orders;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;
import com.hcl.ecommerence.serviceimpl.OrderServiceImpl;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	@Mock
	OrderServiceImpl orderServiceImpl;
	
	@InjectMocks
	OrderController orderController;
	
	static User user;
	static Cart cart;
	static Products products;
	static Orders orders;
	static CartItems cartItems;
	
	@BeforeAll
	public static void setUp() {
		 user = new User(); 
		 user.setUserId(1);
		 user.setUserName("Navya");
		 user.setPassword("abcd");
		 user.setMobileNo(+919875676786L);
		   
		   
		 cart=new Cart();
		 cart.setCartId(1);
		 cart.setProduct(products);
		 cart.setUser(user);
		 
		 cartItems=new CartItems();
		 cartItems.setCartItemsId(1);
		 cartItems.setProduct(products);
		 cartItems.setUser(user);



		
		products = new Products();
		products.setProductId(1);
		products.setProductName("Refrigerator");
		products.setProductPrice(15000);
		
		
		
		orders = new Orders();
		orders.setOrderId(1);
		orders.setUser(user);
		orders.setCart(cartItems);
		
		
	}
	
	@Test
	@DisplayName("OrderItems :Positive Scenario")
	public void testOrderItems() throws CartSizeExceedException {
		when(orderServiceImpl.orderItem(1)).thenReturn("Your order placed successfully");
		ResponseEntity<String> result = orderController.orderItem(1);
		assertEquals("Your order placed successfully",result.getBody());
		
	}
	
	@Test
	@DisplayName("Order Items :negative scenario")
	public void testOrderItems1() throws CartSizeExceedException {
		List<Cart> list = new ArrayList<Cart>();
		assertTrue(list.isEmpty());
		when(orderServiceImpl.orderItem(1)).thenReturn("Empty cart continue shopping");
		ResponseEntity<String> result = orderController.orderItem(1);
		assertEquals("Empty cart continue shopping",result.getBody());
		
	}
	
	@Test
	@DisplayName("Display :positive scenario")
	public void testDisplay() throws CartSizeExceedException {
		List<OrderRequestDto> list = new ArrayList<OrderRequestDto>();
		List<Cart> cartList = new ArrayList<Cart>();
		cartList.add(cart);
		BeanUtils.copyProperties(cartList, list);
		when(orderServiceImpl.displayOrders(1)).thenReturn(list);
		ResponseEntity<List<OrderRequestDto>> result = orderController.displayOrders(1);
		assertEquals(list,result.getBody());

	}
	
	@Test
	@DisplayName("Display :negative scenario")
	public void testDisplay2() throws CartSizeExceedException {
		List<OrderRequestDto> list = new ArrayList<OrderRequestDto>();
		List<Cart> cartList = new ArrayList<Cart>();
		assertTrue(cartList.isEmpty());
		BeanUtils.copyProperties(cartList, list);
		when(orderServiceImpl.displayOrders(1)).thenReturn(list);
		ResponseEntity<List<OrderRequestDto>> result = orderController.displayOrders(1);
		assertEquals(list,result.getBody());
		
	}


}

