package com.ipme.cve.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/cves")
	public ModelAndView getCve(HttpSession session) {
		ModelAndView mav = new ModelAndView("cve/list-cves");
		List<Cve >cveCounts = cveService.findAll();
		int count = 0;
		for (Cve cve : cveCounts) {
			count++;
		}
		int page = 0;
		int pageSize = 12;
		int nbPage = count/pageSize;
		System.out.println(nbPage);
		Page<Cve> cvePage = cveService.findPage(page, pageSize);
		Pageable pageable = cvePage.getPageable();
		mav.addObject("cves", cvePage);
		mav.addObject("hasPrevious", (pageable.next().getPageNumber()+1));
		mav.addObject("cvePage", (pageable.getPageNumber()+1));
		mav.addObject("nbPage", nbPage);
		return mav;
	}
	
	@RequestMapping("/cves/page")
	public ModelAndView getCvePage(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
		ModelAndView mav = new ModelAndView("cve/list-cves");
		page = 0;
		pageSize = 10;
		Page<Cve> cvePage = cveService.findPage(page, pageSize);
		
		mav.addObject("cves", cvePage);
		mav.addObject("hasNext", cvePage.hasNext());
		mav.addObject("hasPrevious", cvePage.hasPrevious());
		
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
