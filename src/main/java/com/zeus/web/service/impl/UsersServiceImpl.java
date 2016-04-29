package com.zeus.web.service.impl;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.web.dao.UsersDao;
import com.zeus.web.model.Permission;
import com.zeus.web.model.Role;
import com.zeus.web.model.Users;
import com.zeus.web.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao usersdao;
	
	@Override
	public Users authentication(Users user) {
		return usersdao.authentication(user);
	}
	@Override
	public void saveUser(Users user) {
		usersdao.saveUser(user);
	}
	@Override
	public Users findUserByUsername(String username) {
		return usersdao.findUserByUsername(username);
	}
	@Override
	public List<Role> findRoles(String username) {
		return usersdao.findRoles(username);
	}
	@Override
	public List<Permission> findpermission(int roleId) {
		return usersdao.findpermission(roleId);
	}
}
