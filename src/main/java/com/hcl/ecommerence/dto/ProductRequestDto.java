package com.hcl.ecommerence.dto;



public class ProductRequestDto {

	
	private int productId;
	

	private String productName;
	

	private float productPrice;
	
	public ProductRequestDto() {
		
	}

	public ProductRequestDto(int productId,  String productName,	float productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	
}

