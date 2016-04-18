package com.zeus.web.dao;

import com.zeus.web.model.Users;

public interface UsersDao {
	/**
     * 用户验证
     */
    Users authentication(Users user);
}
