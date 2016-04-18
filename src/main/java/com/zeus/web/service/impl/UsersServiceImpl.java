package com.zeus.web.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.web.dao.UsersDao;
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

}
