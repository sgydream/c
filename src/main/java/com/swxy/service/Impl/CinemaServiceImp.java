package com.swxy.service.Impl;

import com.swxy.mapper.CinemaMapper;
import com.swxy.mapper.HallMapper;
import com.swxy.mapper.ScheduleMapper;
import com.swxy.pojo.Cinema;
import com.swxy.pojo.Hall;
import com.swxy.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaServiceImp implements CinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private HallMapper hallMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Override
    public List<Cinema> findCinemasByMovieId(long movieId) {
        List<Cinema> cinemaList = this.cinemaMapper.findCinemasByMovieId(movieId);
        System.out.println(movieId);
        System.out.println(cinemaList);
        for (Cinema cinema : cinemaList) {
            List<Hall> hallList = this.hallMapper.findHallByCinemaId(cinema.getCinemaId());
            for (Hall hall : hallList) {
                hall.setScheduleList(this.scheduleMapper.findScheduleByCinemaAndMovieAndHall(hall.getHallId(), hall.getCinemaId(), movieId));
            }
            cinema.setHallList(hallList);
        }
        return cinemaList;
    }

    public void setCinemaMapper(CinemaMapper cinemaMapper) {
        this.cinemaMapper = cinemaMapper;
    }
}
