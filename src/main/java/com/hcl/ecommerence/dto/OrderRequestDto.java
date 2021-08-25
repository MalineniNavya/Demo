package com.hcl.ecommerence.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.hcl.ecommerence.model.CartItems;
import com.hcl.ecommerence.model.User;

public class OrderRequestDto {
	
	
	private int orderId;
				
	private String localDateTime;
		
	
	private CartItems cart;
		
	private User user;
	
	public OrderRequestDto() {
		
	}

	public OrderRequestDto(int orderId, String localDateTime, CartItems cart, User user) {
		super();
		this.orderId = orderId;
		this.localDateTime = localDateTime;
		this.cart = cart;
		this.user = user;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}

	public CartItems getCart() {
		return cart;
	}

	public void setCart(CartItems cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
		


}
