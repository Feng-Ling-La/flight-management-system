package com.hxh.service.Impl;

import com.hxh.bean.Admin;
import com.hxh.bean.User;
import com.hxh.mapper.AdminMapper;
import com.hxh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin FindAdminByName(String adminName)
    {
        return adminMapper.FindAdminByName(adminName);
    }

    @Override
    public User FindUserById(Integer id) {
        return adminMapper.FindUserById(id);
    }

    ;
}
