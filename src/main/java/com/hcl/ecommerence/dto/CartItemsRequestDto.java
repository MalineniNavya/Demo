package com.hcl.ecommerence.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;

public class CartItemsRequestDto {
	
	private int cartItemsId;
	
	private User user;

	

	private Products product;

	
   public CartItemsRequestDto() {
	   
   }


public CartItemsRequestDto(int cartItemsId, User user, Products product) {
	super();
	this.cartItemsId = cartItemsId;
	this.user = user;
	this.product = product;
}


public int getCartItemsId() {
	return cartItemsId;
}


public void setCartItemsId(int cartItemsId) {
	this.cartItemsId = cartItemsId;
}


public User getUser() {
	return user;
}


public void setUser(User user) {
	this.user = user;
}


public Products getProduct() {
	return product;
}


public void setProduct(Products product) {
	this.product = product;
}

}



