package com.swxy.service;

import com.swxy.pojo.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule findScheduleById(long scheduleId);

    Integer delScheduleRemain(long scheduleId);

    List<Schedule> findScheduleByCinemaIdAndMovieId(long cinemaId, long movieId);
}
