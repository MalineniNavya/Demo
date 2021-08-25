package com.hcl.ecommerence.exception;

public class CartSizeExceedException extends Exception{
		private static final long serialVersionUID = 1L;
			
			
			String message;


			public CartSizeExceedException(String message) {
				super(message);
				this.message = message;
			}


			public CartSizeExceedException() {
				super();
			}


		}


