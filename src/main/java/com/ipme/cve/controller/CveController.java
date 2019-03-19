package com.ipme.cve.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Cve;
import com.ipme.cve.model.Product;
import com.ipme.cve.model.ProductCve;
import com.ipme.cve.model.Vendor;
import com.ipme.cve.service.CveService;

@Controller
public class CveController {
	
	
	@Autowired
	private CveService cveService;
	
	@RequestMapping(value="/cve")
	public ModelAndView getCve(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<Cve>cves = cveService.findAll();
		mav.addObject("cves", cves);
		return mav;
	}
	
	@RequestMapping(value="/createCve", method=RequestMethod.GET)
	public ModelAndView createAllCve(HttpSession session) {
		ModelAndView Mav = new ModelAndView("redirect:/");
		Cve cve = new Cve();
		Product product = new Product();
		Vendor vendor = new Vendor();
		ProductCve productCve = new ProductCve();
		cveService.createAllCve(cve, product, vendor, productCve);
		
		return Mav;
	}

}
