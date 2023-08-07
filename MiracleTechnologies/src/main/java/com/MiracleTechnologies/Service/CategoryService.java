package com.MiracleTechnologies.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiracleTechnologies.Model.Category;
import com.MiracleTechnologies.Repository.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	CategoryRepo categoryRepo;
	
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public List<Category> ListCategory() {
		return categoryRepo.findAll();
	}

	 public void editCategory(int categoryId, Category updateCategory) {
		 Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
	        if (optionalCategory.isPresent()) {
	            Category category = optionalCategory.get();
	            category.setCategoryName(updateCategory.getCategoryName());
	            category.setDescription(updateCategory.getDescription());
	            category.setImageUrl(updateCategory.getImageUrl());
	            categoryRepo.save(category);
	        } else {
	            // Handle the case when the category with the given ID is not found.
	            throw new IllegalArgumentException("Category not found with ID: " + categoryId);
	        }
	    }

	public boolean findById(int categoryId) {
		return categoryRepo.findById(categoryId).isPresent();
	}
}
