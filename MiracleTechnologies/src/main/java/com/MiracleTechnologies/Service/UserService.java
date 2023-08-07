package com.MiracleTechnologies.Service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiracleTechnologies.Dto.ResponseDto;
import com.MiracleTechnologies.Dto.User.SignInDto;
import com.MiracleTechnologies.Dto.User.SigninResponseDto;
import com.MiracleTechnologies.Dto.User.SignupDto;
import com.MiracleTechnologies.Exception.AuthenticationFailException;
import com.MiracleTechnologies.Exception.CustomException;
import com.MiracleTechnologies.Model.AuthenticationToken;
import com.MiracleTechnologies.Model.User;
import com.MiracleTechnologies.Repository.UserRepo;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signUp(SignupDto signupDto) {
		//check if user already present
		if(Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))) {
			// we have an user
			throw new CustomException("User already present");
		}
		
		//hash the passoword
		String encryptedPassword = signupDto.getPassword();
		
		try {
			encryptedPassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
		
		User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),encryptedPassword);
		
		userRepo.save(user);
		
		//save the user
		
		
		//create the token
		
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		
		authenticationService.saveConfirmationToken(authenticationToken);
		
		ResponseDto responseDto = new ResponseDto("Sucess","User created successfully");
		return responseDto;
	}


	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}


	public SigninResponseDto signIn(SignInDto signInDto) {
		User user = userRepo.findByEmail(signInDto.getEmail());
		
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("User is not valid");
		}
		
		try {
			if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
				throw new AuthenticationFailException("Wrong Passowrd");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AuthenticationToken token = authenticationService.getToken(user);
		
		if(Objects.isNull(token)) {
			throw new CustomException("Token is not present");
		}
		
		return new SigninResponseDto("Sucess", token.getToken());
	}

}
