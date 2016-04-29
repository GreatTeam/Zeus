package com.zeus.web.util;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zeus.web.model.Permission;
import com.zeus.web.model.Role;
import com.zeus.web.model.Users;
import com.zeus.web.service.UsersService;

@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {
	@Autowired
	private UsersService usersservice;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> role=usersservice.findRoles(username);
        for(Role rol:role){
        	authorizationInfo.addRole(rol.getRole());
        	List<Permission> permission=usersservice.findpermission(rol.getId());
        	for(Permission per:permission){
        		String CRUD=per.getPermission();
        		String module=per.getDescription();
        		if(CRUD.charAt(0)=='1'){
        			authorizationInfo.addStringPermission(module+":Create");
        		}if(CRUD.charAt(1)=='1'){
        			authorizationInfo.addStringPermission(module+":Retrieve");
        		}if(CRUD.charAt(2)=='1'){
        			authorizationInfo.addStringPermission(module+":Update");
        		}if(CRUD.charAt(3)=='1'){
        			authorizationInfo.addStringPermission(module+":Delete");
        		}
        	}
        }
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();  //得到用户名  
        String password = new String((char[])token.getCredentials()); //得到密码
        String MD5=PasswordMD5.getMd5(username, password);
        Users tokenuser=new Users();
        tokenuser.setUsername(username);
        tokenuser.setMD5(MD5);
        Users result=usersservice.authentication(tokenuser);
        if(result==null){
        	throw new IncorrectCredentialsException();
        	
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；  
        return new SimpleAuthenticationInfo(username, password, getName());  
        
    }  
	
}