package com.aswaraj.controller;

import java.security.Principal;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Aswaraj
 *
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Principal principal) {
		if(Objects.nonNull(principal)) {
			return "redirect:/home";
		}
		return "/login";
	}
}
