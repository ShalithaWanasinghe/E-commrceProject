package com.MiracleTechnologies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MiracleTechnologies.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
