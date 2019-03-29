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
import org.w3c.dom.Document;

import com.ipme.cve.model.Cve;
import com.ipme.cve.model.Product;
import com.ipme.cve.model.ProductCve;
import com.ipme.cve.model.Vendor;
import com.ipme.cve.service.CveService;

@Controller
public class CveController {
	
	
	@Autowired
	private CveService cveService;
	
	@RequestMapping(value="/cves", method=RequestMethod.GET)
	public ModelAndView getCve(HttpSession session) {
		ModelAndView mav = new ModelAndView("cve/list-cves");
		int page = 0;
		int pageSize = 12;
		Page<Cve> cvePage = cveService.findPage(page, pageSize);
		Pageable pageable = cvePage.getPageable();
		mav.addObject("cves", cvePage);
		mav.addObject("next", (pageable.next().getPageNumber()+1));
		mav.addObject("cvePage", (pageable.getPageNumber()+1));
		return mav;
	}
	
	@RequestMapping(value="/cves/page")
	public ModelAndView getCvePage(@RequestParam("nb") Integer page, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("cve/list-cves");
		int pageSize = 12;
		Page<Cve> cvePage = cveService.findPage(page-1, pageSize);
		Pageable pageable = cvePage.getPageable();
		mav.addObject("before", (pageable.next().getPageNumber()-1));
		mav.addObject("cves", cvePage);
		mav.addObject("next", (pageable.next().getPageNumber()+1));
		mav.addObject("cvePage", (pageable.getPageNumber()+1));
		return mav;
	}
	
	@RequestMapping(value="/createCve", method=RequestMethod.GET)
	public ModelAndView createRecentCve(HttpSession session) {
		ModelAndView Mav = new ModelAndView("redirect:/");
		Cve cve = new Cve();
		Product product = new Product();
		Vendor vendor = new Vendor();
		ProductCve productCve = new ProductCve();
		cveService.createAllCve(cve, product, vendor, productCve);
		
		return Mav;
	}
	

}
