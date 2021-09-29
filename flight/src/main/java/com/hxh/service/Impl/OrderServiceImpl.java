package com.hxh.service.Impl;

import com.hxh.bean.Order;
import com.hxh.mapper.OrderMapper;
import com.hxh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public int saveOrder(Order order) {
        return orderMapper.saveOrder(order);
    }

    @Override
    public List<Order> findByUserName(String username) {
        return orderMapper.findByUserName(username);
    }

    @Override
    public Order findByUserOrderId(Integer order_id) {
        return orderMapper.findByUserOrderId(order_id);
    }

    @Override
    public int returnFlight(Integer order_id) {
        return orderMapper.returnFlight(order_id);
    }

    @Override
    public void deleteOrder(Integer order_id) {
        orderMapper.deleteOrder(order_id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderMapper.findAllByUsername(username);
    }

    @Override
    public List<Order> findAllByF_num(String f_num) {
        return orderMapper.findAllByF_num(f_num);
    }
}
