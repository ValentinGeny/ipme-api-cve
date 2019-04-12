package com.ipme.cve.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ipme.cve.model.User;
import com.ipme.cve.service.SecurityService;
import com.ipme.cve.service.UserService;



@Controller
public class UserController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegister(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("userForm", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			bindingResult.rejectValue("password", "error.user", "Mot de passe non identique");
		}
		
		
		User userExists = userService.findByEmail(userForm.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Cet email est déjà utilisé");
		}
		
		
		User userUsernameExist = userService.findByUsername(userForm.getUsername());
		if (userUsernameExist != null) {
			bindingResult
				.rejectValue("username", "error.userForm", "Ce pseudo est déjà utilisé");
		}
		
		else {
			userService.save(userForm);
			System.out.println("Auto Login");
			securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
			System.out.println("création sucess");
			modelAndView.addObject("successMessage", "Votre compte a bien été créé");
			modelAndView.addObject("user", userForm);
			modelAndView.setViewName("index");
		}

		return modelAndView;
	}

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
