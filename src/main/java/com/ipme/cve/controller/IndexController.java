package com.ipme.cve.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.configuration.BCryptManagerUtil;
import com.ipme.cve.model.User;
import com.ipme.cve.repository.UserRepository;
import com.ipme.cve.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView getLogin(@RequestParam("email") String email, @RequestParam("password") String password, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		User checkUser = userService.findUserByEmail(email);
		String psswd = BCryptManagerUtil.passwordencoder().encode(password);
		if (checkUser!=null) {
			if (psswd.equals(checkUser.getPassword())) {
				System.out.println("Mdp check");
				mav.setViewName("index");
			}
			else {
				bindingResult.rejectValue("email", "Error login");
			}
			
			if (bindingResult.hasErrors()) {
				mav.setViewName("login");
			}
		}
		
		return mav;
	}
	
	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegister(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Cet email est déjà utilisé");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			
			userRepository.save(user);
			
			modelAndView.addObject("successMessage", "Votre compte a bien été créé");
			modelAndView.setViewName("index");
		}
		
		return modelAndView;
	}
	
	
}
