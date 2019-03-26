package com.ipme.cve.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Product;
import com.ipme.cve.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * List of product
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView getProducts(HttpSession session) {
		ModelAndView mav = new ModelAndView("product/list-products");
		int page = 0;
		int pageSize = 12;
		Page<Product> productPage = productService.findAll(page, pageSize);
		Pageable pageable = productPage.getPageable();
		mav.addObject("products", productPage);
		mav.addObject("next", (pageable.next().getPageNumber()+1));
		mav.addObject("productPage", pageable.getPageNumber()+1);
		return mav;
	}
	
	@RequestMapping(value="/products/page")
	public ModelAndView getProductPage(@RequestParam("nb") Integer page, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("product/list-products");
		int pageSize = 12;
		Page<Product> productPage = productService.findAll(page-1, pageSize);
		Pageable pageable = productPage.getPageable();
		mav.addObject("before", pageable.next().getPageNumber()-1);
		mav.addObject("products", productPage);
		mav.addObject("next", pageable.next().getPageNumber()+1);
		mav.addObject("productPage", pageable.getPageNumber()+1);
		return mav;
	}
}
