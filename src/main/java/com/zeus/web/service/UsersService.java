package com.zeus.web.service;


import java.util.List;

import com.zeus.web.model.Permission;
import com.zeus.web.model.Role;
import com.zeus.web.model.Users;


public interface UsersService {
	/**
     * 用户验证
     */
    Users authentication(Users user);
    /**
     * 注册用户
     */
    void saveUser(Users user);
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
