package com.swxy.pojo;


import java.util.List;

public class Schedule {
    private long scheduleId;
    private long hallId;      //所属放映厅
    private long movieId;     //放映的电影编号
    private String scheduleStartTime; //电影放映时间
    private int schedulePrice; //售价
    private int scheduleRemain;  //剩余座位数
    private int scheduleState;   //场次状态 1：上映中 0：下架
    private Hall scheduleHall; //所属放映厅对象
    private Movies scheduleMovies; //放映的电影
    private List<Orders> ordersList; //所有的订单集合

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", hallId=" + hallId +
                ", movieId=" + movieId +
                ", scheduleStartTime='" + scheduleStartTime + '\'' +
                ", schedulePrice=" + schedulePrice +
                ", scheduleRemain=" + scheduleRemain +
                ", scheduleState=" + scheduleState +
                ", scheduleHall=" + scheduleHall +
                ", scheduleMovies=" + scheduleMovies +
                ", ordersList=" + ordersList +
                '}';
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public int getSchedulePrice() {
        return schedulePrice;
    }

    public void setSchedulePrice(int schedulePrice) {
        this.schedulePrice = schedulePrice;
    }

    public int getScheduleRemain() {
        return scheduleRemain;
    }

    public void setScheduleRemain(int scheduleRemain) {
        this.scheduleRemain = scheduleRemain;
    }

    public int getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(int scheduleState) {
        this.scheduleState = scheduleState;
    }

    public Hall getScheduleHall() {
        return scheduleHall;
    }

    public void setScheduleHall(Hall scheduleHall) {
        this.scheduleHall = scheduleHall;
    }

    public Movies getScheduleMovies() {
        return scheduleMovies;
    }

    public void setScheduleMovies(Movies scheduleMovies) {
        this.scheduleMovies = scheduleMovies;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
