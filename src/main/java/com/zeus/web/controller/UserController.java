package com.zeus.web.controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.web.model.Users;
import com.zeus.web.service.UsersService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UsersService usersservice;
	@RequestMapping(value="/login")
	public String userLogin(String username,String password,Model model, HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token= new UsernamePasswordToken(username,password);
		subject.login(token);
		return "index2";
	}
	@RequestMapping(value="/saveRegister")
	public String saveregister(String username,String password,Model model, HttpServletRequest request){
		String salt =username+"zeus";
		String mD5 = new Md5Hash(password, salt,2).toString();
		Users user=new Users();
		user.setUsername(username);
		user.setPassword(password);
		user.setMD5(mD5);
		user.setSalt(salt);
		user.setCreatetime(new Date());
		usersservice.saveUser(user);
		return "redirect:/login";
	}
	@RequestMapping("ajaxName")
	public void ajaxName(String username,Model model,HttpServletRequest request,HttpServletResponse response){
		System.out.println("ajaxName");
		Users users=usersservice.findUserByUsername(username);
		String sign;
		if(users!=null){
			//未注册返回1
			sign="1";
		}else{
			//已注册返回2
			sign="2";
		}
		try {
			PrintWriter writer=response.getWriter();
			writer.append(sign);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
