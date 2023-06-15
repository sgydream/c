package com.swxy.mapper;

import com.swxy.pojo.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MovieMapper {
    Movies findMovieById(long movieId);

    Integer changeMovieBoxOff(@Param("movieBoxOffice") float movieBoxOffice, @Param("movieId") long movieId);

    List<Movies> findAllMovies(int i);

    List<Movies> sortMovieByBoxOffice();

    List<Movies> findMoviesLikeType(String type);

    Integer addMovieCommentCount(long movieId);

    Integer deleteMovieCommentCount(long movieId);
}
