package com.hcl.ecommerence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerence.model.Cart;
import com.hcl.ecommerence.model.CartItems;


@Repository
public interface CartItemsDao extends JpaRepository<CartItems,Integer> {
	/*
	 * @Query(value="select * from cart c where c.user_id=:userId",nativeQuery=true)
	 * List<Cart> displayCart(@Param("userId") int userId);
	 */
	public CartItems findByCartItemsId(int cartId);
	public List<CartItems> findCartItemsByUserUserId(int userId);



}
