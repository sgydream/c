package com.swxy.controller;

import com.alibaba.fastjson.JSONObject;

import com.swxy.pojo.Cinema;
import com.swxy.pojo.Movies;
import com.swxy.service.CinemaService;
import com.swxy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaService cinemaService;

    @RequestMapping( "/findAllMovies")
    @ResponseBody
    public JSONObject findAllMovies() {
        JSONObject obj = new JSONObject();
        List<Movies> list = movieService.findAllMovies(1);
        List<Movies> upcomingList = movieService.findAllMovies(0);
        List<Movies> offList = movieService.sortMovieByBoxOffice();
        String type[] = {"喜剧", "动作", "爱情", "动画", "科幻", "惊悚", "冒险", "犯罪", "悬疑"};
        ArrayList<Object> typeArr = new ArrayList<Object>();
        for (int i = 0; i < type.length; i++) {
            List<Movies> moviesList = this.movieService.findMoviesLikeType(type[i]);
            float boxOffice = 0;
            for (int j = 0; j < moviesList.size(); j++) {
                boxOffice += moviesList.get(j).getMovieBoxOffice();
            }
            JSONObject typeJson = new JSONObject();
            typeJson.put(type[i], boxOffice);
            typeArr.add(typeJson);
        }
        obj.put("code", 0);
        obj.put("count", list.size());
        obj.put("upcomingCount", upcomingList.size());
        obj.put("data", list);
        obj.put("data1", upcomingList);
        obj.put("sort", offList);
        obj.put("type", typeArr);
        return obj;
    }


//    @RequestMapping("/findAllMovies")
//    @ResponseBody
//    public JSONObject hello() {
//        JSONObject obj = new JSONObject();
//        obj.put("hello", 1);
//        return obj;
//    }


    @RequestMapping("findMovieById")
    @ResponseBody
    public JSONObject findMovieById(@RequestParam("movieId") long movieId) {
        JSONObject obj = new JSONObject();
        Movies movie = movieService.findMovieById(movieId);
        List<Cinema> list = this.cinemaService.findCinemasByMovieId(movieId);
        obj.put("code", 0);
        obj.put("data", movie);
        obj.put("cinemaList", list);
        obj.put("cinemaCount", list.size());
        return obj;
    }


}
