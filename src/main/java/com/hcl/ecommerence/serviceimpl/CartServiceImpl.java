package com.hcl.ecommerence.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.ecommerence.dao.CartDao;
import com.hcl.ecommerence.dao.CartItemsDao;
import com.hcl.ecommerence.dao.ProductDao;
import com.hcl.ecommerence.dao.UserDao;
import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.CartItems;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;
import com.hcl.ecommerence.service.CartService;

@Service
public class CartServiceImpl  implements CartService{
	
	
	@Autowired
	CartDao cartDao;
	
	
	@Autowired
	CartItemsDao cartItemsDao;


	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Override
	public String addToCart(int userId, int productId) throws CartSizeExceedException {
		if(!userDao.existsById(userId)) {
			throw new CartSizeExceedException("UserId does not exists");
		}
		
		if(!productDao.existsById(productId)) {
			throw new CartSizeExceedException("ProductId does not exists");
		}
		Cart cart = new Cart();
		Products products = new Products();
		User user = new User();
		ProductRequestDto productRequestDto = new ProductRequestDto();
		products = productDao.getById(productId);
		user = userDao.getById(userId);
		BeanUtils.copyProperties(products, productRequestDto);
		cart.setProduct(products);
		cart.setUser(user);
		CartItems cartItems = new CartItems();
		
		BeanUtils.copyProperties(cart, cartItems);
        cartItemsDao.save(cartItems);
		
		cartDao.save(cart);
		return "Product added to cart successfully";

	}
	
	@Override
	public List<ProductRequestDto> displayCart(int userId) throws CartSizeExceedException{
		if(!userDao.existsById(userId)) {
			throw new CartSizeExceedException("UserId does not exists");
		}
		List<ProductRequestDto> list1 = new ArrayList<ProductRequestDto>();
		
		List<Cart> list = cartDao.findCartByUserUserId(userId);
		System.out.println(list);
		if(!list.isEmpty()) {
		for (Cart cart : list) {
			ProductRequestDto productRequestDto = new ProductRequestDto();
			Products products = new Products();
			products=productDao.findByProductId(cart.getProduct().getProductId());
			
			BeanUtils.copyProperties(products, productRequestDto);
			//productRequestDto.setProductPrice(products.getProductPrice());
			list1.add(productRequestDto);
			
		}
	}
		else
			throw new CartSizeExceedException("Items doesn't exist in the cart");
		return list1;
	}

}
