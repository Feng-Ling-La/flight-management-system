package com.hxh.mapper;

import com.hxh.bean.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对订单表进行操作
 */
@Mapper
public interface OrderMapper {

    /**
     * 查询所有订单，返回一个List集合
     * @return
     */
    @Select("select * from f_order ")
    public List<Order> findAll();


    /**
     * 向订单表插入一条订单信息，该方法在用户订票时使用。这些参数分别对应用户在买票时填写的信息，
     * 并将去封装为一个 order对象传入使用。
     * @param order 要保存的订单信息
     * @return 插入成功则返回一个大于0的数字，反之，否然。
     */
    //添加一个用户订单
   /* @Insert("insert  into f_order (f_num,username,userID,o_date,f_type,phone,f_price,f_id,id) VALUES" +
            "(#{f_num},#{username},#{userID},#{o_date},#{f_type},#{phone},#{f_price},#{f_id},#{id})")
    public int saveOrder(Order order);*/
    @Insert("insert  into f_order (f_num,username,userID,f_type,phone,f_price,f_id,id) VALUES" +
            "(#{f_num},#{username},#{userID},#{f_type},#{phone},#{f_price},#{f_id},#{id})")
    public int saveOrder(Order order);
    /**
     * 根据用户名查询订单信息。查询出 用户本人 的订票信息
     *  作用：
     *      用户点击 “个人中心”  查询订单信息时使用。
     * @param username 用户本人的 用户名
     * @return 返回一个List，是因为一个用户可能会订多张票
     */
    @Select("select * from f_order where username=#{userName}")
    public List<Order> findByUserName(String username);

    /**
     * 根据订单的id 返回一个订单对象。
     * @param order_id 要查询的订单编号
     * @return
     */
    @Select("select * from f_order where order_id=#{order_id}")
    public Order findByUserOrderId(Integer order_id);

    /**
     * 删除一个订单信息，根据订单编号（id）删除。
     *  作用：
     *      退票。
     * @param order_id 用户要退的票的编号
     * @return 删除成功（即：退票成功），则返回一个大于0的数，反之，则返回一个小于0 的数字。
     */
    @Delete("DELETE FROM `f_order` WHERE `order_id`=#{order_id}")
    public int returnFlight(Integer order_id);


    /**
     * 删除一个订单信息。
     *  该方法和上面的 “returnFlight” 方法一模一样，但是这个方法是管理员在删除订单信息时使用。
     * @param order_id
     */
    @Delete("delete from f_order where order_id=#{order_id}")
    public void deleteOrder(Integer order_id);


    //更新order_id所指订单
    @Update("update f_order set id=#{id}, username=#{username}, f_id=#{f_id}, f_num=#{f_num}, o_date=#{o_date}, f_type=#{f_type}, f_price=#{f_price}, phone=#{phone}, userID=#{userID} where order_id=#{order_id}")
    public int updateOrder(Order order);

    //通过用户名查找订单
    @Select("select * from f_order where username=#{username}")
    public List<Order> findAllByUsername(String username);

    //通过航班号查找订单
    @Select("select * from f_order where f_num=#{f_num}")
    public List<Order> findAllByF_num(String f_num);


}

