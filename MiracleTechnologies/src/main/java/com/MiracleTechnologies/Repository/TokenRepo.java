package com.MiracleTechnologies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiracleTechnologies.Model.AuthenticationToken;
import com.MiracleTechnologies.Model.User;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer>{
	AuthenticationToken findByUser(User user);
	
	AuthenticationToken findByToken(String token);
}
