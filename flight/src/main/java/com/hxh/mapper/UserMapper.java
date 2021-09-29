package com.hxh.mapper;

import com.hxh.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对User表进行操
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所以用户，返回一个list集合，List<User>中的User表示这个集合中的每一个元素都是User类对象
     * @return
     */
    @Select("select * from user")
    public List<User> findAll();

    /**
     * 根据用户名查询出用户返回，返回是一个用户对象
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    public User FindUserByUsername(String username);

    @Select("select * from user where realName=#{realName}")
    public User FindUserByRealName(String realName);

    @Select("select * from user where identityNum=#{identityNum}")
    public User FindUserByIdentityNum(String identityNum);

    /**
     * 根据电话号码返回用户。
     * 作用：
     *      注册用户的时候会用到，用来判断该电话号是否存在，若存在，则提示电话号已注册，
     *      提示用户重新注册。
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    public User FindUserByPhone(String phone);


    /**
     * 根据Email返回用户。
     * 作用：（和上面的 “FindUserByPhone” 相同）
     *      注册用户的时候会用到，用来判断该Email是否存在，若存在，则提示Email已注册，
     *      提示用户重新注册。
     *
     *
     * @param email
     * @return
     */
    @Select("select * from user where email=#{email}")
    public User FindUserByEmail(String email);


    @Select("select * from user where id=#{id}")
    public User FindUserById(Integer id);



    /**
     * 根据用户id删除用户，因为id是主键，可以唯一确定一个用户。
     * @param id 要删除的用户的id
     * @return 返回一个int型数据，大于0，则表示删除成功，反之失败+
     */
    @Delete("delete from user where id=#{id}")
    public int deleteUserById(Integer id);

    /**
     * 向用户表中新增一个用户，在注册的时候用到该方法。
     *      username,password,Phone,email 这四个参数，分别是用户注册时填写的信息，
     *      用户在注册之前，先回调用上面的 “FindUserByPhone” 和 “FindUserByEmail” 方法保证用户的
     *      电话号和Email唯一，才能继续注册。
     * @param user 将用户注册时填写的username,password,Phone,email 这四个属性封装为一个User对象传进来使用
     */
    @Insert("insert into user(username,password,realName,identityNum,Phone,email) values(#{username},#{password},#{realName},#{identityNum},#{Phone},#{email})")
    public void saveUser(User user);

    @Update("update user set username=#{username}, password=#{password}, realName=#{realName},identityNum=#{identityNum}, Phone=#{Phone}, email=#{email} where id=#{id}")
    public int updateUser(User user);


}
