package com.hxh.service;

import com.hxh.bean.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User FindUserByUsername(String username);

    public User FindUserByRealName(String realName);

    public User FindUserByIdentityNum(String identityNum);

    public User FindUserByPhone(String phone);

    public User FindUserByEmail(String email);

    public User FindUserById(Integer id);

    public int deleteUserById(Integer id);

    public void saveUser(User user);

    public int updateUser(User user);




}
