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
	public String getHealth() { return "Your Entertainment, SIMPLIFIED !"; }

	@GetMapping(value = "/termsCondition", produces = "text/html")
	public ModelAndView termsCondition(Model model) {
		return new ModelAndView("TermsCondition");
	}

	@GetMapping(value = "/faqHtml")
	public ModelAndView getFaq(Model model) {
		return new ModelAndView("faq");
	}

	@GetMapping(value = "/faqJson", produces = "application/json")
	public String fetchJson() {
		String json = "[\n" +
				"  {\n" +
				"    \"question\" : \"What is the difference between an individual membership and a family membership?\",\n" +
				"    \"answer\" : \"An individual membership provides Sort Switch Online service to the Sort Account that purchased the service. Individual memberships are available in 1-month, 3-month, or 1-year increments.\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"question\" : \"Can I change the members in my family membership?\",\n" +
				"    \"answer\" : \"Yes. Members added to the owners family group will be included in the family membership plan for as long as they remain in the group.\"\n" +
				"  },\n" +
				"  {\n" +
				"  \"question\" : \"Can Sort Accounts with different country settings share a family membership?\",\n" +
				"  \"answer\" : \"Yes. However, not all features are available in all countries.\"\n" +
				"  }\n" +
				"]";
		return json;
	}
}