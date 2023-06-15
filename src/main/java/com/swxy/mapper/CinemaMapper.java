package com.swxy.mapper;


import com.swxy.pojo.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface CinemaMapper {
    Cinema findCinemaById(long cinemaId);


    List<Cinema> findCinemasByMovieId(long movieId);
}
