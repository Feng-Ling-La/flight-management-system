package com.hxh.service;

import com.hxh.bean.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();

    public int saveOrder(Order order);

    public List<Order> findByUserName(String username);

    public Order findByUserOrderId(Integer order_id);

    public int returnFlight(Integer order_id);

    public void deleteOrder(Integer order_id);

    public int updateOrder(Order order);

    public List<Order> findAllByUsername(String username);

    public List<Order> findAllByF_num(String f_num);
}
