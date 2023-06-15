package com.swxy.service.Impl;


import com.swxy.mapper.*;
import com.swxy.pojo.Hall;
import com.swxy.pojo.Orders;
import com.swxy.pojo.Schedule;
import com.swxy.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private HallMapper hallMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Schedule findScheduleById(long scheduleId) {
        Schedule schedule = this.scheduleMapper.findScheduleById(scheduleId);
        Hall hall = this.hallMapper.findHallById(schedule.getHallId());
        hall.setHallCinema(this.cinemaMapper.findCinemaById(hall.getCinemaId()));
        schedule.setScheduleHall(hall);
        schedule.setScheduleMovies(this.movieMapper.findMovieById(schedule.getMovieId()));
        List<Orders> list = this.orderMapper.findOrdersByScheduleId(scheduleId);
        schedule.setOrdersList(list);
        return schedule;
    }

    @Override
    public Integer delScheduleRemain(long scheduleId) {
        return this.scheduleMapper.addScheduleRemain(scheduleId);
    }

    @Override
    public List<Schedule> findScheduleByCinemaIdAndMovieId(long cinemaId, long movieId) {
        List<Schedule> list = this.scheduleMapper.findScheduleByCinemaAndMovie(cinemaId, movieId);
        for (Schedule schedule : list) {
            Hall hall = this.hallMapper.findHallById(schedule.getHallId());
            hall.setHallCinema(this.cinemaMapper.findCinemaById(hall.getCinemaId()));
            schedule.setScheduleHall(hall);
            schedule.setScheduleMovies(this.movieMapper.findMovieById(schedule.getMovieId()));
        }
        return list;
    }

    public void setScheduleMapper(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }
}
