package com.swxy.mapper;

import com.swxy.pojo.Hall;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface HallMapper {
    Hall findHallById(long hallId);

    List<Hall> findHallByCinemaId(long cinemaId);
}
