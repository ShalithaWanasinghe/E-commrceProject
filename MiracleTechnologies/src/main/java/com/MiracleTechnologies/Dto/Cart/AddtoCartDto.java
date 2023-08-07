package com.MiracleTechnologies.Dto.Cart;

import jakarta.validation.constraints.NotNull;

public class AddtoCartDto {
	private int id;
	private @NotNull int productId;
	private @NotNull int quantity;
	
	public AddtoCartDto() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
