package com.eDecree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eDecree.utility.GlobalFunction;




@Controller
@RequestMapping("/admin")
public class AdminController {

	static int seq = 0;
	private GlobalFunction globalfunciton;

	public AdminController() {
		// TODO Auto-generated constructor stub
		globalfunciton = new GlobalFunction();
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminHome() {
		
		return "/admin/home";

	}
	
	@RequestMapping(value = "/dmscase", method = RequestMethod.GET)
	public String dms() {
		
		return "/admin/dms";

	}
	@RequestMapping(value = "/onlinecasefiling", method = RequestMethod.GET)
	public String onineCaseFiling() {
		
		return "/admin/efiling";

	}
	
	
}
