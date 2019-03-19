package com.ipme.cve.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Product;
import com.ipme.cve.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/products")
	public ModelAndView getProducts(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Product>products = productService.findAll();
		mav.addObject("products", products);
		return mav;
	}

}
