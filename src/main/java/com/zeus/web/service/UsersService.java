package com.zeus.web.service;

import com.zeus.web.model.Users;


public interface UsersService {
	/**
     * 用户验证
     */
    Users authentication(Users user);
}
