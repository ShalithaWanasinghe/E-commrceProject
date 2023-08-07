package com.MiracleTechnologies.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiracleTechnologies.Dto.ResponseDto;
import com.MiracleTechnologies.Dto.User.SignInDto;
import com.MiracleTechnologies.Dto.User.SigninResponseDto;
import com.MiracleTechnologies.Dto.User.SignupDto;
import com.MiracleTechnologies.Service.UserService;

@RequestMapping("user")
@RestController()
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signUp(signupDto);
	}
	
	@PostMapping("/signin")
	public SigninResponseDto signIn(@RequestBody SignInDto signInDto) {
		return userService.signIn(signInDto);
	}
}
