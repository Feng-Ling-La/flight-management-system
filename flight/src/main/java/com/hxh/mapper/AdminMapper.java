package com.hxh.mapper;

import com.hxh.bean.Admin;
import com.hxh.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 对Admin表进行操作
 */
@Mapper
public interface AdminMapper {


    /**
     * 查询Admin表，在管理员登录的时候使用该方法
     * 根据 admin 的adminName 拿到管理员
     * @param adminName 要登录的管理员的username
     * @return
     */
    @Select("select * from admin where adminName=#{adminName}")
    public Admin FindAdminByName(String adminName);

    @Select("select * from user where id=#{id}")
    public User FindUserById(Integer id);


}
