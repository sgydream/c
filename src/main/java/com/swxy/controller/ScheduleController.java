package com.swxy.controller;

import com.alibaba.fastjson.JSONObject;

import com.swxy.pojo.Schedule;
import com.swxy.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;
    @RequestMapping(value = "findScheduleById",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findScheduleById(@RequestParam("scheduleId")long scheduleId) {
        JSONObject obj = new JSONObject();
        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        obj.put("code", 0);
        obj.put("data",schedule);
        return obj;
    }

    @RequestMapping("findScheduleByCinemaAndMovie")
    @ResponseBody
    public JSONObject findScheduleByCinemaAndMovie(@RequestParam("cinemaId")long cinemaId,@RequestParam("movieId")long movieId) {
        JSONObject obj = new JSONObject();
        List<Schedule> list = scheduleService.findScheduleByCinemaIdAndMovieId(cinemaId, movieId);
        obj.put("code",0);
        obj.put("count",list.size());
        obj.put("data", list);
        return obj;
    }
}
