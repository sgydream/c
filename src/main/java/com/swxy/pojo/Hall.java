package com.swxy.pojo;


import java.util.List;


public class Hall {
	private long hallId;
	private String hallName;  //放映厅名称
	private int hallCapacity; //放映厅容量
	private long cinemaId;  //所属影院编号
	private Cinema hallCinema; //所属影院
	private List<Schedule> ScheduleList; //所有的电影场次集合

	@Override
	public String toString() {
		return "Hall{" +
				"hallId=" + hallId +
				", hallName='" + hallName + '\'' +
				", hallCapacity=" + hallCapacity +
				", cinemaId=" + cinemaId +
				", hallCinema=" + hallCinema +
				", ScheduleList=" + ScheduleList +
				'}';
	}

	public long getHallId() {
		return hallId;
	}

	public void setHallId(long hallId) {
		this.hallId = hallId;
	}

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	public int getHallCapacity() {
		return hallCapacity;
	}

	public void setHallCapacity(int hallCapacity) {
		this.hallCapacity = hallCapacity;
	}

	public long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Cinema getHallCinema() {
		return hallCinema;
	}

	public void setHallCinema(Cinema hallCinema) {
		this.hallCinema = hallCinema;
	}

	public List<Schedule> getScheduleList() {
		return ScheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		ScheduleList = scheduleList;
	}
}
