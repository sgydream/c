package com.swxy.mapper;

import com.swxy.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper

public interface OrderMapper {
    List<Orders> findOrdersByScheduleId(long scheduleId);

    Integer addOrder(Orders orders);

}
