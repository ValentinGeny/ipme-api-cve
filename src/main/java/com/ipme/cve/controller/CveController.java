package com.ipme.cve.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.Cve;
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
	
	@RequestMapping(value="/createCve")
	public ModelAndView createAllCve() {
		ModelAndView Mav = new ModelAndView();
		
		
		return Mav;
	}

}
