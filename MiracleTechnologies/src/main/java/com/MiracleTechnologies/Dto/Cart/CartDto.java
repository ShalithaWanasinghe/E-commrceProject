package com.MiracleTechnologies.Dto.Cart;

import java.util.List;

public class CartDto {
	List<CartItemDto> cartItems;
	private double totalCost;
	
	public CartDto() {
		super();
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
}
