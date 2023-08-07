package com.MiracleTechnologies.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiracleTechnologies.Dto.Cart.AddtoCartDto;
import com.MiracleTechnologies.Dto.Cart.CartDto;
import com.MiracleTechnologies.Dto.Cart.CartItemDto;
import com.MiracleTechnologies.Exception.CustomException;
import com.MiracleTechnologies.Model.Cart;
import com.MiracleTechnologies.Model.Product;
import com.MiracleTechnologies.Model.User;
import com.MiracleTechnologies.Repository.CartRepo;

@Service
public class CartService {
	@Autowired
	ProductService productService;
	
	@Autowired
	CartRepo cartRepo;

	public void addToCart(AddtoCartDto addToCartDto, User user) {
		Product product = productService.findById(addToCartDto.getProductId());
		
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		cartRepo.save(cart);
	}

	public CartDto listCartItems(User user) {
		List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		
		List<CartItemDto> cartItems = new ArrayList<>();
		
		double totalCost = 0;
		for (Cart cart:cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalCost);
		cartDto.setCartItems(cartItems);
		
		return cartDto;
	}

	public void DeleteCartItems(int cartItemId, User user) {
		Optional<Cart> optionalCart = cartRepo.findById(cartItemId);
		
		if(optionalCart.isEmpty()) {
			throw new CustomException("Cart item id is invalid: "+ cartItemId);
		}
		
		Cart cart = optionalCart.get();
		
		if (cart.getUser() != user) {
			throw new CustomException("Cari item does not belong to user: "+cartItemId);
		}
		
		cartRepo.delete(cart);
	}

}
