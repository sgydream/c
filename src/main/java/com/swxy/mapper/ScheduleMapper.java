package com.swxy.mapper;

import com.swxy.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    Schedule findScheduleById(long scheduleId);
    Integer delScheduleRemain(long scheduleId);
    Integer addScheduleRemain(long scheduleId);



    List<Schedule> findScheduleByCinemaAndMovieAndHall(@Param("hallId") long hallId, @Param("cinemaId")long cinemaId, @Param("movieId")long movieId);

    List<Schedule> findScheduleByCinemaAndMovie(@Param("cinemaId") long cinemaId, @Param("movieId") long movieId);
}

