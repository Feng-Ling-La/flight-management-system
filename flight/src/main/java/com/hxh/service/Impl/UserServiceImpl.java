package com.hxh.service.Impl;

import com.hxh.bean.User;
import com.hxh.mapper.UserMapper;
import com.hxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User FindUserByUsername(String username) {
        return userMapper.FindUserByUsername(username);
    }

    @Override
    public User FindUserByRealName(String realName){return userMapper.FindUserByRealName(realName);}

    @Override
    public User FindUserByIdentityNum(String identityNum) {
        return userMapper.FindUserByIdentityNum(identityNum);
    }

    @Override
    public User FindUserByPhone(String phone) {
        return userMapper.FindUserByPhone(phone);
    }

    @Override
    public User FindUserByEmail(String email) {
        return userMapper.FindUserByEmail(email);
    }

    @Override
    public User FindUserById(Integer id) {
        return userMapper.FindUserById(id);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
