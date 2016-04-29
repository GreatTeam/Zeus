package com.zeus.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;












import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zeus.web.model.Users;
import com.zeus.web.service.UsersService;
import com.zeus.web.util.PasswordMD5;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UsersService usersservice;
	@RequestMapping(value="/main")
	public String userMain(Model model, HttpServletRequest request){
		return "main";
	}
	@RequestMapping(value="/login")
	public String userLogin(String username,String password,String verification,String hiddenVerification,Model model, HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		// 已登陆则 跳到首页
        if (subject.isAuthenticated()) {
            return "redirect:/user/main";
        }
		if(username==null||password==null){
			return "redirect:/login";
		}
		//验证码
		if(verification!=null&hiddenVerification!=null){
			Session session=subject.getSession(); 
			String value=(String) session.getAttribute(hiddenVerification);
			if(verification.toLowerCase().equals(value.toLowerCase())){
				System.out.println("true");
			}else{
				System.out.println(value+"-----"+verification);
				return "redirect:/login";
			}
		}
		UsernamePasswordToken token= new UsernamePasswordToken(username,password);
		try{
			subject.login(token);
		}catch(IncorrectCredentialsException e){
			System.out.println("密码错误");
		}
		return "redirect:/user/main";
	}
	@RequestMapping(value="/saveRegister", method = RequestMethod.POST)
	public String saveregister(String username,String password,Model model, HttpServletRequest request){
		if(username==null||password==null){
			return "redirect:/login";
		}
		String mD5 = PasswordMD5.getMd5(username, password);
		Users user=new Users();
		user.setUsername(username);
		user.setPassword(password);
		user.setMD5(mD5);
		user.setSalt(username+"zeus");
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
			//已注册返回
			sign="false";
		}else{
			//未注册
			sign="true";
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
	@RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        // 登出操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
