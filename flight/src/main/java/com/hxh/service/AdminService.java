package com.hxh.service;

import com.hxh.bean.Admin;
import com.hxh.bean.User;

public interface AdminService {
    public Admin FindAdminByName(String adminName);

    public User FindUserById(Integer id);
}
