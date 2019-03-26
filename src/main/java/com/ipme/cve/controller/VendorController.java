package com.ipme.cve.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Vendor;
import com.ipme.cve.service.VendorService;

@Controller
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(value="/vendors")
	public ModelAndView getVendors(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("vendor/list-vendors");
		int page = 0;
		int pageSize = 12;
		Page<Vendor> vendorPage = vendorService.findAll(page, pageSize);
		Pageable pageable = vendorPage.getPageable();
		mav.addObject("vendors", vendorPage);
		mav.addObject("next", (pageable.next().getPageNumber()+1));
		mav.addObject("vendorPage", (pageable.getPageNumber()+1));
		return mav;
	}
	
	@RequestMapping(value="/vendors/page")
	public ModelAndView getProductPage(@RequestParam("nb") Integer page, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("vendor/list-vendors");
		int pageSize = 12;
		Page<Vendor> vendorPage = vendorService.findAll(page-1, pageSize);
		Pageable pageable = vendorPage.getPageable();
		mav.addObject("before", (pageable.next().getPageNumber()-1));
		mav.addObject("vendors", vendorPage);
		mav.addObject("next", (pageable.next().getPageNumber()+1));
		mav.addObject("vendorPage", (pageable.getPageNumber()+1));
		return mav;
	}
}
