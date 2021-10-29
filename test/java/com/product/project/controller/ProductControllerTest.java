package com.product.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.project.dto.InsertProductRequestDTO;
import com.product.project.model.Product;
import com.product.project.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest
{

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	WebApplicationContext context;
	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mockMvc;

	private InsertProductRequestDTO productRequestDTO;

	private Product product;

	@BeforeEach
	void setUp()
	{
		initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		productRequestDTO = new InsertProductRequestDTO();
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());
		productRequestDTO.setName("Blue Shirt");
		productRequestDTO.setBrand("Hugo Boss");
		productRequestDTO.setTags(List.of("blue", "shirt"));
		productRequestDTO.setCategory("apparel");

		product = new Product();
		product.setName("Blue Shirt");
		product.setBrand("Hugo Boss");
		product.setTags(List.of("blue", "shirt"));
		product.setCategory("apparel");
		product.setCreated_at(nowAsISO);
		product.setId(UUID.randomUUID());
	}

	@Test
	void testInsertEndPoint_WithStatus201() throws Exception
	{
		String productDtoString = objectMapper.writeValueAsString(productRequestDTO);
		mockMvc.perform(post("/v1/products/insert")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.characterEncoding("UTF-8")
								.content(productDtoString))
								.andDo(print())
								.andExpect(status().isCreated());
	}

	@Test
	void testGetAllProductsByCategoryEndPoint_WithStatus201() throws Exception
	{
		mockMvc.perform(get("/v1/products/category?categoryValue=apparel")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testGetAllProductsByCategoryEndPoint_WithStatus400() throws Exception
	{
		mockMvc.perform(get("/v1/products/category?categoryValue=")
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON)
								.characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
