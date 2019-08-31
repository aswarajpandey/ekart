package com.aswaraj.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aswaraj.model.User;
import com.aswaraj.service.UserService;

@Controller
public class RegistrationController {
	
	UserService userService;
	
	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/registration");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/registration", method =  RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/registration");
		if(userService.findByEmail(user.getUserEmail()).isPresent()) {
			bindingResult.rejectValue("userEmail", "error.user", "User already exists with similar Email");
		}
		
		if(userService.findByUsername(user.getUserUsername()).isPresent()) {
			bindingResult.rejectValue("userUsername", "error.user", "User already exists with similar Username");
		}
		
		if(!bindingResult.hasErrors()) {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully.");
			modelAndView.addObject("user", new User());
		}
		return modelAndView;
	}
}
