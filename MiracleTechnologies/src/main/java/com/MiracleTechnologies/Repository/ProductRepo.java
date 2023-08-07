package com.MiracleTechnologies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiracleTechnologies.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
