package com.MiracleTechnologies.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiracleTechnologies.Dto.ProductDto;
import com.MiracleTechnologies.Exception.ProductNotExistingException;
import com.MiracleTechnologies.Model.Category;
import com.MiracleTechnologies.Model.Product;
import com.MiracleTechnologies.Repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	ProductRepo productRepo;

	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setCategory(category);
		product.setPrice(productDto.getPrice());
		productRepo.save(product);
	}
	
	public ProductDto getProductDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setDescription(product.getDescription());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setId(product.getId());
		return productDto;
	}

	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		for(Product product: allProducts) {
			productDtos.add(getProductDto(product));
		}
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, int productId) throws Exception {
		Optional<Product> optionalproduct = productRepo.findById(productId);
		if(!optionalproduct.isPresent()) {
			throw new Exception("Product not present");
		}
		Product product = optionalproduct.get();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		productRepo.save(product);
	}

	public Product findById(int productId) throws ProductNotExistingException{
		Optional<Product> optionalProduct  = productRepo.findById(productId);
		if(optionalProduct.isEmpty()) {
			throw new ProductNotExistingException("Product id is invalidL "+ productId);
		}
		return optionalProduct.get();
	}
}
