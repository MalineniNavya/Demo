 package com.hcl.ecommerence.serviceimpl;
  
import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.hcl.ecommerence.dao.CartItemsDao;
import com.hcl.ecommerence.dao.OrdersDao; 
import com.hcl.ecommerence.dao.ProductDao; 
import com.hcl.ecommerence.dao.UserDao;
import com.hcl.ecommerence.dto.OrderRequestDto;
import com.hcl.ecommerence.exception.CartSizeExceedException;
import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.CartItems; 
import com.hcl.ecommerence.model.Orders;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.model.User;
  
  
  @ExtendWith(MockitoExtension.class) 
  public class OrderServiceImplTest {
  
  
  @Mock 
  CartDao cartDao;
  
  @Mock 
  OrdersDao ordersDao;
  
  @Mock 
  CartItemsDao cartIemsDao;
  
  @Mock 
  UserDao userDao;
  
  @Mock 
  ProductDao productDao;
  
  @InjectMocks 
  OrderServiceImpl orderServiceImpl;
  
  
  static User user; 
  static Cart cart; 
  static CartItems cartItems; 
  static Products products; 
  static Orders orders;
  
  
  
  @BeforeAll 
  public static void setUp() {
  
  user = new User(); 
  user.setUserId(1); 
  user.setUserName("Navya");
  user.setPassword("abcd");
  user.setMobileNo(+919875676786L);
  
  products = new Products();
  products.setProductId(1);
  products.setProductName("Refrigerator"); 
  products.setProductPrice(15000);
  
  
  cartItems = new CartItems(); 
  cartItems.setCartItemsId(1);
  cartItems.setProduct(products); 
  cartItems.setUser(user);
  
  
  
  orders = new Orders();
  orders.setCart(cartItems);
  orders.setOrderId(1);
  orders.setUser(user);
  orders.setLocalDateTime("24-08-2021");
  
  }
  
  
  @Test
  @DisplayName(value="Order Items:Positive scenario") 
  public void testOrderItems() {
		List<CartItems> caritemstList = new ArrayList<CartItems>();
		caritemstList.add(cartItems);
		System.out.println(caritemstList);
	    when(userDao.existsById(1)).thenReturn(true);
	    when(cartIemsDao.findCartItemsByUserUserId(1)).thenReturn(caritemstList);
		assertFalse(caritemstList.isEmpty());
		List<Products> productList=new ArrayList<Products>();
		for(CartItems cart:caritemstList) {
		}	

  
  }
  
	@Test
	@DisplayName(value="OrderItems: Negative scenario")
	public void testOrderItems2() throws CartSizeExceedException {
		
		when(userDao.existsById(1)).thenReturn(false);
		assertThrows(CartSizeExceedException.class,()->orderServiceImpl.orderItem(1));
		
	}
	
	
	
	@Test
	@DisplayName(value="Display Orders :Positive scenario")
	public void testDislay() throws CartSizeExceedException {
		
		List<Orders> orderList=new ArrayList<Orders>();
		orderList.add(orders);
		assertFalse(orderList.isEmpty());
		when(userDao.existsById(1)).thenReturn(true);
		when(ordersDao.findOrdersByUserUserId(1)).thenReturn(orderList);
		List<OrderRequestDto> list = new ArrayList<OrderRequestDto>();
		
		
		//when(orderRepository.displayOrders(1)).thenReturn(orderList);

		for(Orders order:orderList) {
			OrderRequestDto orderRequestDto = new OrderRequestDto();
			
			when(productDao.findByProductId(1)).thenReturn(products);
			BeanUtils.copyProperties(order, orderRequestDto);
			BeanUtils.copyProperties(products, orderRequestDto);
			list.add(orderRequestDto);
			}
		List<OrderRequestDto> orderList1 = orderServiceImpl.displayOrders(1);
		assertEquals(list,orderList1);
	}



  }
 