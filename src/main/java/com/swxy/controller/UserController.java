package com.swxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.swxy.pojo.Userss;
import com.swxy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public JSONObject login(String userName, String userPassword, HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        Userss user = userService.login(userName, userPassword);
        System.out.println(userName);
        System.out.println(userPassword);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.getUserRole() == 0) {
                obj.put("msg", "usersuccess");
                obj.put("data", user);
                return obj;
            } else {
                obj.put("msg", "adminsuccess");
                obj.put("data", user);
                return obj;
            }
        }
        obj.put("msg", "fail");
        return obj;
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index";
    }

}
