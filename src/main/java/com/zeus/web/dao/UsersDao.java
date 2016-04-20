package com.zeus.web.dao;

import org.apache.ibatis.annotations.Param;

import com.zeus.web.model.Users;

public interface UsersDao {
	/**
     * 用户验证
     */
    Users authentication(@Param("user")Users user);
    /**
     * 注册用户
     */
    void saveUser(@Param("user")Users user);
    /**
     * 验证用户账号是否已注册
     */
    Users findUserByUsername(String username);
}
