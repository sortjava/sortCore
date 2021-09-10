package com.sort.sortcore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/static")
public class staticController {
	private static final Logger log = LoggerFactory.getLogger(staticController.class);

	@GetMapping({"/home"})
	public String getHealth() {
		return "Your Entertainment, SIMPLIFIED !";
	}

	@GetMapping(value = "/termsCondition", produces = "text/html")
	public ModelAndView termsCondition(Model model) {
		return new ModelAndView("TermsCondition");
	}
}