package com.swxy.service;

import com.swxy.pojo.Movies;

import java.util.List;

public interface MovieService {
    Integer changeMovieBoxOffice(float price,long movieId);

    List<Movies> findAllMovies(int movieState);

    List<Movies> sortMovieByBoxOffice();

    List<Movies> findMoviesLikeType(String type);

    Movies findMovieById(long movieId);

    Integer addCommentCount(long movieId);

    Integer delCommentCount(long movieId);
}
