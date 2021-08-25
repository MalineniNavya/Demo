package com.hcl.ecommerence.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.hcl.ecommerence.dao.CartDao;
import com.hcl.ecommerence.dao.ProductDao;
import com.hcl.ecommerence.dao.UserDao;
import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;



@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
	
	
	@Mock
	ProductDao productDao;
	
	@Mock
	UserDao userDao;
	
	@Mock 
	CartDao cartDao;
	
	
	@InjectMocks
	CartServiceImpl cartServiceImpl;
	
	static Cart cart;
	static User user;
	static Products product;
	static ProductRequestDto productRequestDto;
	
	@BeforeAll()
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
					
		
	}
	
	@Test
	@DisplayName("Add to Cart:Positive scenario")
	public void testAddToCart() throws CartSizeExceedException {
		
		cart = new Cart();
		ProductRequestDto productRequestDto = new ProductRequestDto();
		when(userDao.existsById(1)).thenReturn(true);
		when(userDao.existsById(1)).thenReturn(true);
		when(userDao.getById(1)).thenReturn(user);
		when(productDao.getById(1)).thenReturn(product);
		
		BeanUtils.copyProperties(product, productRequestDto);
		cart.setCartId(1);
		cart.setProduct(product);
		cart.setUser(user);
		String result = cartServiceImpl.addToCart(1, 1);
		assertEquals("Product added to cart successfully",result);
		
	}
	
	@Test
	@DisplayName(value="Add to Cart:Negative scenario")
	public void testAddToCart2() throws CartSizeExceedException {
		
		when(userDao.existsById(1)).thenReturn(true);
		when(productDao.existsById(1)).thenReturn(false);
		assertThrows(CartSizeExceedException.class,()->cartServiceImpl.addToCart(1,1));
	}
	
	@Test
	@DisplayName(value="Add to Cart:Negative scenario")
	public void testAddToCart3() throws CartSizeExceedException {
		//when(productRepository.existsById(1)).thenReturn(true);
		when(userDao.existsById(1)).thenReturn(false);
		
		assertThrows(CartSizeExceedException.class,()->cartServiceImpl.addToCart(1,1));
	}
	
	@Test
	@DisplayName("Display Items in cart:Positive Scenario")
	public void testDisplay() throws CartSizeExceedException {
		when(userDao.existsById(1)).thenReturn(true);
		when(productDao.existsById(1)).thenReturn(true);
		ProductRequestDto productRequestDto = new ProductRequestDto();
		List<ProductRequestDto> list1 = new ArrayList<ProductRequestDto>();
		List<ProductRequestDto> resultList = new ArrayList<ProductRequestDto>();
		List<Cart> cartList = new ArrayList<Cart>();
		cart.setCartId(1);
		cart.setProduct(product);
		cart.setUser(user);
		cartList.add(cart);
		product = new Products();
		product.setProductId(1);
		product.setProductName("Refrigerator");
		product.setProductPrice(15000);

		when(cartDao.findCartByUserUserId(1)).thenReturn(cartList);
		for(Cart cart:cartList)
		BeanUtils.copyProperties(product, productRequestDto);
		list1.add(productRequestDto);
		//when(cartDao.displayCart(1)).thenReturn(cartList); 
		resultList=cartServiceImpl.displayCart(user.getUserId());
		assertEquals(list1,resultList);

		
	}
	
	@Test
	@DisplayName("Display Items in cart:Negative scenario")
	public void testDisplay2() throws CartSizeExceedException{
		when(userDao.existsById(1)).thenReturn(false);
		assertThrows(CartSizeExceedException.class,()->cartServiceImpl.displayCart(1));
		
	}
		
}

	
	
	
