package com.hcl.ecommerence.model;

import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;



@Entity
public class Orders {
		
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
				
		
	private String localDateTime;
		
	@ManyToOne
	private CartItems cart;
		
	@ManyToOne
	private User user;
		
	public Orders() {
			
		}
	

	public Orders(int orderId,String localDateTime, CartItems cart, User user) {
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


