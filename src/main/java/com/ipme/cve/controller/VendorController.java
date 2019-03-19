package com.ipme.cve.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Vendor;
import com.ipme.cve.service.VendorService;

@Controller
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@RequestMapping(value="/vendors")
	public ModelAndView getVendors(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		List<Vendor> vendors = vendorService.findAll();
		mav.addObject("vendors", vendors);
		return mav;
	}
}
