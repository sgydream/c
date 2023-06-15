package com.swxy.controller;

import com.alibaba.fastjson.JSONObject;

import com.swxy.pojo.Orders;
import com.swxy.pojo.Schedule;
import com.swxy.pojo.Userss;
import com.swxy.service.MovieService;
import com.swxy.service.OrderService;
import com.swxy.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private ScheduleService scheduleService;

    @Resource
    private MovieService movieService;

    @RequestMapping(value = "findScheduleById")
    @ResponseBody
    public JSONObject findScheduleById(@RequestParam("schedule_id") long schedule_id) {
        JSONObject obj = new JSONObject();
        Schedule schedule = scheduleService.findScheduleById(schedule_id);
        obj.put("code", 0);
        obj.put("data", schedule);
        return obj;
    }


    @RequestMapping("buyTickets")
    @ResponseBody
    public JSONObject buyTickets(@RequestParam("scheduleId") long scheduleId, @RequestParam("position[]") String[] position, @RequestParam("price") int price, HttpServletRequest request) {
        Userss userss = (Userss) request.getSession().getAttribute("user");
//        Userss userss ={
//                userId = 1,
//    userName = user,
//   userPassword = user, userEmail = null,
//    userPhone = 15826287840, userRole = 0, userHead = null}
        ;
        System.out.println(userss);
        JSONObject obj = new JSONObject();
        if (userss == null) {
            obj.put("code", 200);
            obj.put("msg", "您未登录,登录之后才可购票~");
        } else {
            String orderID = "";
            int done = 0;
            int orderPrice = price / position.length;
            String userId = "";
            switch (String.valueOf(userss.getUserId()).length()) {
                case 1:
                    userId = "000" + String.valueOf(userss.getUserId());
                    break;
                case 2:
                    userId = "00" + String.valueOf(userss.getUserId());
                    break;
                case 3:
                    userId = "0" + String.valueOf(userss.getUserId());
                    break;
                case 4:
                    userId = String.valueOf(userss.getUserId());
                    break;
            }
            for (int i = 0; i < position.length; i++) {
                String positionS = String.join("", position[i]);
                positionS = positionS.replace("'", ""); // 去除单引号
                positionS = positionS.replace("[", "").replace("]", ""); // 去除方括号

                Orders orders = new Orders();
                String orderId = "";
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                orderId += dateFormat.format(date);
                orderId += System.currentTimeMillis();
                orderId += userId;
                String index = "";
                switch (position[i].length()) {
                    case 4:
                        index = "0" + position[i].replaceAll("排", "0");
                        index = index.replaceAll("座", "");
                        break;
                    case 5:
                        if (position[i].charAt(2) >= 48 && position[i].charAt(2) <= 57) {
                            index = "0" + position[i].replaceAll("排", "");
                            index = index.replaceAll("座", "");
                        } else {
                            index = position[i].replaceAll("排", "0");
                            index = index.replaceAll("座", "");
                        }
                        break;
                    case 6:
                        index = position[i].replaceAll("排", "");
                        index = index.replaceAll("座", "");
                        break;
                }
                orderId += index;
                orders.setOrderId(orderId);
//                orders.setOrderPosition(position[i]);
                orders.setOrderPosition(positionS);
                orders.setScheduleId(scheduleId);
                orders.setUserId(userss.getUserId());
                orders.setOrderPrice(orderPrice);
                orders.setOrderTime(new Date());
                Integer rs = this.orderService.addOrder(orders);
                Integer rs1 = this.scheduleService.delScheduleRemain(scheduleId);
                done++;
//                PayController.sendRequestToAlipay(orderId, orderPrice);
                orderID = orderId;
            }
            String message = String.valueOf(position);
            float orderPriceF = (float) price;

//            PayController.sendRequestToAlipay(orderID, orderPriceF, message);
            System.out.println("已执行");


            if (done == position.length) {
                float sum = (float) price / 10000;
                Integer rs2 = this.movieService.changeMovieBoxOffice(sum, this.scheduleService.findScheduleById(scheduleId).getMovieId());
                obj.put("code", 0);
                obj.put("msg", "购票成功~");
            } else {
                obj.put("code", 200);
                obj.put("msg", "购票失败~");
            }
        }

        return obj;
    }

//    @ResponseBody
//    @RequestMapping("alipay")
//    public String alipay(HttpSession session, Model model,
//                         @RequestParam("price") int price) throws AlipayApiException {
//        //把dona_id项目id 放在session中
////        session.setAttribute("dona_id",dona_id);
//
//        //生成订单号（支付宝的要求？）
////        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
////        String user = UUID.randomUUID().toString().replace("-","").toUpperCase();
////
////        String OrderNum = time+user;
//        String OrderNum = "12131313";
//        float price1 = (float) price;
//        System.out.println(price1);
//
//        //调用封装好的方法（给支付宝接口发送请求）
//        return PayController.sendRequestToAlipay(OrderNum, price1, "ghjk");
//    }

}
