package com.product.project.service;

import com.product.project.model.Product;
import com.product.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;

	public Product insertProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		if(Objects.nonNull(product)) {
			return savedProduct;
		}
		else {
			return null;
		}
	}

	public List<Product> getAllProductsByCategory(String category) {
		List<Product> products = productRepository.findByCategory(category);
		if(products == null) {
			return null;
		}
		else {
			products.sort(new Comparator<Product>()
			{
				@Override public int compare(Product o1, Product o2)
				{
					String createdDate1 = o1.getCreated_at();
					String createdDate2 = o2.getCreated_at();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					try
					{
						Date date1 = format.parse(createdDate1);
						Date date2 = format1.parse(createdDate2);
						return Long.compare(date2.getTime(),date1.getTime());
					}
					catch (ParseException e)
					{
						e.printStackTrace();
					}
					return 0;
				}
			});
		}
		return products;
	}

}
