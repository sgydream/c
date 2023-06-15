package com.swxy.service.Impl;


import com.swxy.mapper.*;
import com.swxy.pojo.Orders;
import com.swxy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private HallMapper hallMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public Integer addOrder(Orders orders) {
        return this.orderMapper.addOrder(orders);

    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
