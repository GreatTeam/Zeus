package com.zeus.web.controller;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.web.util.Verification;

@Controller
public class LogController {
	
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request){
		System.out.println("login");
		return "login";
	}
	@RequestMapping("/verification")
	public void verification(String reMath,HttpServletRequest request,HttpServletResponse response){
		System.out.println("verification");
		Subject subject = SecurityUtils.getSubject();
		Session session=subject.getSession();
		//随机生成字符串，并写入session
        String code = Verification.getRandCode(4);
        if(reMath!=null){
        	session.setAttribute(reMath,code);
        }
        BufferedImage image = Verification.getImage(100,30, code, 5);
        response.setContentType("image/png");
        
        OutputStream out;
		try {
			out = response.getOutputStream();
			out.write(Verification.getByteArray(image));
	        out.flush();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
