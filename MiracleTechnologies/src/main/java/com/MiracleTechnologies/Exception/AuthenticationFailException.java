package com.MiracleTechnologies.Exception;

public class AuthenticationFailException extends IllegalArgumentException{
	public AuthenticationFailException(String msg) {
		super(msg);
	}
}
