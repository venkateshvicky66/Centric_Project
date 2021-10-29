package com.product.project.service;

import com.product.project.model.Product;
import com.product.project.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest
{
	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private Product product;

	@BeforeEach
	void setUp()
	{
		initMocks(this);

		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		product = new Product();
		product.setName("Blue Shirt");
		product.setBrand("Hugo Boss");
		product.setTags(List.of("blue", "shirt"));
		product.setCategory("apparel");
		product.setCreated_at(nowAsISO);
		product.setId(UUID.randomUUID());
	}

	@Test
	void insertProductTestWhenSaveIsSuccessful()
	{
		Mockito.when(productRepository.save(product)).thenReturn(product);

		Product actualProduct = productService.insertProduct(product);

		Assertions.assertNotNull(actualProduct);
		Assertions.assertEquals(product.toString(), actualProduct.toString());
	}

	@Test
	void insertProductTestWhenSaveIsUnSuccessful()
	{
		Mockito.when(productRepository.save(product)).thenReturn(null);

		Product actualProduct = productService.insertProduct(product);

		Assertions.assertNull(actualProduct);
	}

	@Test
	void getAllProductsBasedOnCategoryWhenCategoryIsPresent()
	{
		Mockito.when(productRepository.findByCategory(any(String.class))).thenReturn(List.of(product));

		List<Product> actualProducts = productService.getAllProductsByCategory("apparel");

		Assertions.assertNotNull(actualProducts);
		Assertions.assertTrue(actualProducts.size() > 0);
	}


	@Test
	void getAllProductsBasedOnCategoryWhenCategoryIsNotPresentInDB()
	{
		Mockito.when(productRepository.findByCategory(any(String.class))).thenReturn(Collections.emptyList());

		List<Product> actualProducts = productService.getAllProductsByCategory("apparel");

		Assertions.assertNotNull(actualProducts);
		Assertions.assertEquals(0, actualProducts.size());
	}
}
