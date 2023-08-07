package com.MiracleTechnologies.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiracleTechnologies.Common.ApiResponse;
import com.MiracleTechnologies.Dto.ProductDto;
import com.MiracleTechnologies.Model.Category;
import com.MiracleTechnologies.Repository.CategoryRepo;
import com.MiracleTechnologies.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category does not exists"),HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(productDto, optionalCategory.get());
		return new ResponseEntity<>(new ApiResponse(true, "Product has been added"),HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ProductDto>> getProducts(){
		List<ProductDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") int productId,@RequestBody ProductDto productDto) throws Exception{
		Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category does not exists"),HttpStatus.BAD_REQUEST);
		}
		productService.updateProduct(productDto, productId);
		return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"),HttpStatus.OK);
	}
}
