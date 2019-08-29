package com.aswaraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.aswaraj.exception.NotEnoughStockException;
import com.aswaraj.service.CartService;
import com.aswaraj.service.ProductService;

/**
 * @author Aswaraj
 *
 */
@Controller
public class EkartController {

	private final CartService cartService;

	private final ProductService productService;

	@Autowired
	public EkartController(CartService cartService, ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping("/cart")
	public ModelAndView cart() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/cart");
		modelAndView.addObject("products", cartService.getProductsInCart());
		modelAndView.addObject("total", cartService.getTotal().toString());
		return modelAndView;
	}
	
	@GetMapping("/cart/addProduct/{productId}")
	public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
		// product -> cartService.addProductToCart(product) can be written as cartService::addProductToCart
		productService.findById(productId).ifPresent(cartService::addProductToCart);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/cart");
		modelAndView.addObject("products", cartService.getProductsInCart());
		modelAndView.addObject("total", cartService.getTotal().toString());
		return modelAndView;
	}
	
	@GetMapping("/cart/removeProduct/{productId}")
	public ModelAndView removeProductFromCart(@PathVariable("productId") long productId) {
		// product -> cartService.removeProductFromCart(product) can be written as cartService::removeProductFromCart
		productService.findById(productId).ifPresent(cartService::removeProductFromCart);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/cart");
		modelAndView.addObject("products", cartService.getProductsInCart());
		modelAndView.addObject("total", cartService.getTotal().toString());
		return modelAndView;
	}
	
	@GetMapping("/cart/checkout")
	public ModelAndView checkOut() {
		try {
			cartService.checkOut();
		} catch (NotEnoughStockException notEnoughStockException) {
			return cart().addObject("outOfStockMessage", notEnoughStockException.getMessage());
		}
		return cart().addObject("successmessage", "Cart items has been successfully checked out.");
	}

}
