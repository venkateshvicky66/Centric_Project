package com.product.project.controller;

import com.product.project.dto.InsertProductRequestDTO;
import com.product.project.model.Product;
import com.product.project.response.ErrorResponse;
import com.product.project.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.lang.String;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/products/")
public class ProductController
{
	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(@RequestBody InsertProductRequestDTO insertProductRequestDTO)
	{
		Product product = modelMapper.map(insertProductRequestDTO, Product.class);
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		product.setCreated_at(nowAsISO);
		Product product1 = productService.insertProduct(product);
		ResponseEntity<?> response;
		if(product1!=null)
		{
			 response =new ResponseEntity<>(product1, HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<>(new ErrorResponse("Unable to create product", nowAsISO), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/category")
	public ResponseEntity<?> getAllProductByCategories(@RequestParam("categoryValue") String category)
	{
		System.out.println(category);
		ResponseEntity<?> response;
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		java.lang.String nowAsISO = df.format(new Date());
		if(category == null || !StringUtils.hasLength(java.lang.String.valueOf(category))) {

			response = new ResponseEntity<>(new ErrorResponse("Please enter a valid category", nowAsISO), HttpStatus.BAD_REQUEST);
		}
		else {
			List<Product> products = productService.getAllProductsByCategory(category);
			if(products == null) {
				response = new ResponseEntity<>(new ErrorResponse("Error Fetching All the products", nowAsISO), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else {
				response = new ResponseEntity<>(products, HttpStatus.OK);
			}
		}
		return response;
	}
}
