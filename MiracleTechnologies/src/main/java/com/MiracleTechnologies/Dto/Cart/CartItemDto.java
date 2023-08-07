package com.MiracleTechnologies.Dto.Cart;

import com.MiracleTechnologies.Model.Cart;
import com.MiracleTechnologies.Model.Product;

public class CartItemDto {
	private int id;
	private int quantity;
	private Product product;
	
	public CartItemDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public CartItemDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.setProduct(cart.getProduct());
	}
	
}
