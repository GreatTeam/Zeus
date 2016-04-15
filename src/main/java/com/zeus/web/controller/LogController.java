package com.zeus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {
	
	@RequestMapping("/login")
	public String login(){
		System.out.println("1");
		return "login";
	}

}
