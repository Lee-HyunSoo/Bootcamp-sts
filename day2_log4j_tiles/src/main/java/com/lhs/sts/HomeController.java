package com.lhs.sts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String homeMain() {
		return "main";
	}
	
}
