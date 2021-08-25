package com.hcl.ecommerence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerence.model.Products;


@Repository
public interface ProductDao extends JpaRepository<Products,Integer> {
 
	
	public List<Products> findByProductNameContains(String productName);
    Products findByProductId(int productId);



}
