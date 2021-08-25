package com.hcl.ecommerence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CartItems {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartItemsId;
	
	@OneToOne
	private User user;

	
	@ManyToOne
	private Products product;

	
   public CartItems() {
	   
   }


public CartItems(int cartItemsId, User user, Products product) {
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
