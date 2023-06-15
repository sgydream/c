package com.swxy.pojo;


import java.util.Date;

public class Orders {
	private String orderId;
	private long userId;   //所属用户编号
	private long scheduleId;    //所属场次编号,通过schedule_id-》场次信息-》放映厅信息+电影信息
	private String orderPosition; //电影票座位信息
	private int orderState;  //订单状态 0：退票中 -1：无法退票  1：已支付 2：退票成功
	private int orderPrice; //订单价格
	private Date orderTime; //订单支付时间
	private Userss orderUserss;   //所属用户对象
	private Schedule orderSchedule; //所属电影场次

	@Override
	public String toString() {
		return "Orders{" +
				"orderId='" + orderId + '\'' +
				", userId=" + userId +
				", scheduleId=" + scheduleId +
				", orderPosition='" + orderPosition + '\'' +
				", orderState=" + orderState +
				", orderPrice=" + orderPrice +
				", orderTime=" + orderTime +
				", orderUserss=" + orderUserss +
				", orderSchedule=" + orderSchedule +
				'}';
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getOrderPosition() {
		return orderPosition;
	}

	public void setOrderPosition(String orderPosition) {
		this.orderPosition = orderPosition;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Userss getOrderUserss() {
		return orderUserss;
	}

	public void setOrderUserss(Userss orderUserss) {
		this.orderUserss = orderUserss;
	}

	public Schedule getOrderSchedule() {
		return orderSchedule;
	}

	public void setOrderSchedule(Schedule orderSchedule) {
		this.orderSchedule = orderSchedule;
	}
}
