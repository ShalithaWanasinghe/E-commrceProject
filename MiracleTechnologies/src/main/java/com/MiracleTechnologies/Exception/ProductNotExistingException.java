package com.MiracleTechnologies.Exception;

public class ProductNotExistingException extends IllegalArgumentException {
	public ProductNotExistingException(String msg) {
		super(msg);
	}
}
