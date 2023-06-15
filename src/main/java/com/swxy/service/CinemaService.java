package com.swxy.service;

import com.swxy.pojo.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> findCinemasByMovieId(long movieId);
}
