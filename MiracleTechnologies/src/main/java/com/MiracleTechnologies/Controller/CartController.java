package com.MiracleTechnologies.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MiracleTechnologies.Common.ApiResponse;
import com.MiracleTechnologies.Dto.Cart.AddtoCartDto;
import com.MiracleTechnologies.Dto.Cart.CartDto;
import com.MiracleTechnologies.Model.User;
import com.MiracleTechnologies.Service.AuthenticationService;
import com.MiracleTechnologies.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddtoCartDto addToCartDto, @RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		cartService.addToCart(addToCartDto, user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Add to cart"),HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		CartDto cartDto = cartService.listCartItems(user);
		
		return new ResponseEntity<>(cartDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemId,@RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		cartService.DeleteCartItems(itemId, user);
		
		return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"),HttpStatus.OK);
	}
}
