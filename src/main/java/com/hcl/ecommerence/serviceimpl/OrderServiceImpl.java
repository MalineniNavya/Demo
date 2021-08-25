package com.hcl.ecommerence.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
import com.hcl.ecommerence.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	OrdersDao orderDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartItemsDao cartItemDao;
	
	@Autowired
	
	ProductDao productDao;
	
	


	@Override
	public String orderItem(int userId) throws CartSizeExceedException {
		if(!userDao.existsById(userId)) {
			throw new  CartSizeExceedException("User Id does not exists");
		}
			List<CartItems> cartItems = cartItemDao.findCartItemsByUserUserId(userId);
			List<Products> productList=new ArrayList<Products>();
			if(!cartItems.isEmpty()) {
			for(CartItems itemList1:cartItems) {
				Orders orders = new Orders();
				CartItems cartItem11 =new CartItems();
				cartItem11.setCartItemsId(itemList1.getCartItemsId());
				cartItem11=cartItemDao.findByCartItemsId(itemList1.getCartItemsId());
				User user = new User();
				user=userDao.findByUserId(itemList1.getUser().getUserId());
				
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
				orders.setLocalDateTime(LocalDateTime.now().format(format));
				orders.setCart(cartItem11);
				orders.setUser(user);
				
				OrderRequestDto orderRequestDto = new OrderRequestDto();
				BeanUtils.copyProperties(itemList1,orderRequestDto);
				orderDao.saveAndFlush(orders);
				
			}
			cartDao.deleteAll();
			}else 
				throw new CartSizeExceedException("Empty cart.. continue shopping");
			return "Order placed successfully";
		}
	
	
	


	@Override
	public List<OrderRequestDto> displayOrders(int userId) throws CartSizeExceedException {

		if(!userDao.existsById(userId)) {
			throw new CartSizeExceedException("User Id does not exists");
		}
		List<OrderRequestDto> list = new ArrayList<OrderRequestDto>();
		List<Products> productList = new ArrayList<Products>();
		List<Orders> orderList = orderDao.findOrdersByUserUserId(userId);
		if(!orderList.isEmpty()) {
		for(Orders order:orderList) {
			OrderRequestDto orderRequestDto = new OrderRequestDto();
			Products products = new Products();
			products=productDao.findByProductId(order.getCart().getProduct().getProductId());
			//productList.add(products);
			BeanUtils.copyProperties(order, orderRequestDto);
			BeanUtils.copyProperties(products, orderRequestDto);
			list.add(orderRequestDto);
		}
		}else
			throw new CartSizeExceedException("You haven`t ordered anything yet.. Place your first order");
		return list;
	}





	@Override
	public String orderItem1(int userId) throws CartSizeExceedException {
		// TODO Auto-generated method stub
		return null;
	}

		
	

}