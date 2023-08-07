package com.MiracleTechnologies.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiracleTechnologies.Model.Cart;
import com.MiracleTechnologies.Model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
