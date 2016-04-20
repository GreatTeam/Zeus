package com.zeus.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {
	
	
	@RequestMapping("/login")
	public String login(){
		System.out.println("login");
		return "login";
	}
	@RequestMapping("/register")
	public String register(){
		System.out.println("register");
		return "register";
	}
    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }
}
