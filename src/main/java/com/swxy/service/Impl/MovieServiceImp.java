package com.swxy.service.Impl;


import com.swxy.mapper.CommentMapper;
import com.swxy.mapper.MovieMapper;
import com.swxy.mapper.UserMapper;
import com.swxy.pojo.Comment;
import com.swxy.pojo.Movies;
import com.swxy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer changeMovieBoxOffice(float price, long movieId) {
        return this.movieMapper.changeMovieBoxOff(price, movieId);

    }

    @Override
    public List<Movies> findAllMovies(int movieState) {
        return this.movieMapper.findAllMovies(movieState);

    }

    @Override
    public List<Movies> sortMovieByBoxOffice() {
        return this.movieMapper.sortMovieByBoxOffice();
    }

    @Override
    public List<Movies> findMoviesLikeType(String type) {
        return this.movieMapper.findMoviesLikeType(type);

    }

    @Override
    public Movies findMovieById(long movieId) {
        Movies movie = this.movieMapper.findMovieById(movieId);
        List<Comment> list = this.commentMapper.findCommentsByMovieId(movieId);
        for (Comment comment : list) {
            comment.setCommentUserss(this.userMapper.findUserById(comment.getUserId()));
        }
        movie.setCommentList(list);
        return movie;
    }

    public static void main(String[] args) {
        MovieServiceImp movieServiceImp = new MovieServiceImp();
        movieServiceImp.findAllMovies(1);
    }
    @Override
    public Integer addCommentCount(long movieId) {
        return this.movieMapper.addMovieCommentCount(movieId);
    }

    @Override
    public Integer delCommentCount(long movieId) {
        return this.movieMapper.deleteMovieCommentCount(movieId);
    }

    public void setMovieMapper(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }
}

