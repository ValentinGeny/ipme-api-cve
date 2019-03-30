package com.ipme.cve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.service.ScrapingService;

@Controller
public class ScrapingController {
	
	@Autowired
	private ScrapingService scrapingService;

	@RequestMapping(value="/test")
	public ModelAndView testScraping() {
		ModelAndView mav = new ModelAndView("redirect:/");
		scrapingService.scrapingHtml();
		return mav;
	}
	
	@Scheduled(cron="0 0 4 * * ?")
	public void scraping() {
		scrapingService.scrapingHtml();
	}
}
