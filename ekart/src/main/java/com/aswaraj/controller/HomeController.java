package com.aswaraj.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aswaraj.model.Product;
import com.aswaraj.service.ProductService;
import com.aswaraj.util.Pager;

/**
 * @author Aswaraj
 *
 */
@Controller
public class HomeController {
	
	private static final int INITIAL_PAGE = 0;
	
	private static final int RECORD_PER_PAGE = 5;
	
	private final ProductService productService;
	
	@Autowired
	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/home")
	public ModelAndView home(@RequestParam("page") Optional<Integer> page) {
		
		int evalPage = page.orElse(0) < 1 ? INITIAL_PAGE : page.get() - 1;
		
		Page<Product> products = productService.findAllProductsPageable(new PageRequest(evalPage, RECORD_PER_PAGE));
		Pager pager = new Pager(products);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", products);
		modelAndView.addObject("pager", pager);
		modelAndView.setViewName("/home");
		
		return modelAndView;
	}
}
