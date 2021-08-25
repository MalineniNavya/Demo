package com.hcl.ecommerence.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.ecommerence.dao.ProductDao;
import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.ProductAlreadyExistException;
import com.hcl.ecommerence.model.Products;
import com.hcl.ecommerence.service.ProductService;



  @Service 
  public class ProductServiceImpl implements ProductService {
  
  
  @Autowired 
  ProductDao productDao;

@Override
public List<ProductRequestDto> searchProduct(String productName) throws ProductAlreadyExistException  {
	Products products =new Products();
	
	List<Products> productList=new ArrayList<Products>();
	List<ProductRequestDto> List = new ArrayList<ProductRequestDto>();
	productList=productDao.findByProductNameContains(productName);
	System.out.println(productList);
	if(productList.size()!=0) {
	for(Products product:productList) {
		ProductRequestDto productResponse = new ProductRequestDto();
		products.setProductName(product.getProductName());
		products.setProductPrice(product.getProductPrice());
		BeanUtils.copyProperties(products, productResponse);
		System.out.println(product);
		System.out.println(productResponse);
		List.add(productResponse);
		System.out.println(List);
	}
	return List;
	}else
		throw new ProductAlreadyExistException ("Product with ProdctName does not exist");
}

@Override
public Products getProductById(int productId) {
	return productDao.getById(productId);


      }

   }