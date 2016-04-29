package com.zeus.web.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zeus.web.model.Permission;
import com.zeus.web.model.Role;
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
    /**
     * 查询用户角色
     */
    List<Role> findRoles(String username);
    /**
     * 查询用户权限
     */
    List<Permission> findpermission(int roleId);
}
