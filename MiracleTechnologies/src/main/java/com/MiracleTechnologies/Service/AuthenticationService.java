package com.MiracleTechnologies.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiracleTechnologies.Exception.AuthenticationFailException;
import com.MiracleTechnologies.Model.AuthenticationToken;
import com.MiracleTechnologies.Model.User;
import com.MiracleTechnologies.Repository.TokenRepo;

@Service
public class AuthenticationService {

	@Autowired
	TokenRepo tokenRepo;
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepo.save(authenticationToken);
		
	}
	public AuthenticationToken getToken(User user) {
		return tokenRepo.findByUser(user);
	}
	
	public User getUser(String token) {
		final AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
		if(Objects.isNull(token)) {
			return null;
		}
		return authenticationToken.getUser();
	}
	
	public void authenticate(String token) {
		if(Objects.isNull(token)) {
			throw new AuthenticationFailException("Token not present");
		}
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailException("Token not valid");
		}
	}
}
