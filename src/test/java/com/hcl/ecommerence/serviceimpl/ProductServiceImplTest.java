package com.hcl.ecommerence.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.hcl.ecommerence.dao.ProductDao;
import com.hcl.ecommerence.dto.ProductRequestDto;
import com.hcl.ecommerence.exception.ProductAlreadyExistException;
import com.hcl.ecommerence.model.Products;



@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	ProductDao productDao;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	static Products products;
	static ProductRequestDto productRequestDto;


	@Test
	@DisplayName("Search Product:Positive scenario")
	public void testSearchProduct() throws ProductAlreadyExistException {
		products = new Products();
		List<Products> list = new ArrayList<Products>();
		products.setProductId(1);
		products.setProductName("Refrigerator");
		products.setProductPrice(15000);
		list.add(products);
		when(productDao.findByProductNameContains("Refrigerator")).thenReturn(list);
		assertTrue(list.size() != 0);
		productRequestDto = new ProductRequestDto();
		BeanUtils.copyProperties(products, productRequestDto);
		List<ProductRequestDto> dtoList = new ArrayList<ProductRequestDto>();
		dtoList.add(productRequestDto);
		List<ProductRequestDto> list1 = productServiceImpl.searchProduct("Refrigerator");
		assertEquals(dtoList, list1);
	}

	@Test
	@DisplayName("Search Product:Negative scenario") 
	public void testSearchProduct2() throws ProductAlreadyExistException {
		List<Products> list = new ArrayList<Products>();
		when(productDao.findByProductNameContains("tv")).thenReturn(list);
		assertTrue(list.size() == 0);
		assertThrows(ProductAlreadyExistException.class, () -> productServiceImpl.searchProduct("tv"));

	}
}

