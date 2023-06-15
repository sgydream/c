package com.swxy.service.Impl;


import com.swxy.mapper.UserMapper;
import com.swxy.pojo.Userss;
import com.swxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper usermapper;
    @Override
    public Userss login(String userName, String userPassword) {
        List<Userss> userList = usermapper.findUserByName(userName);
        for(Userss user : userList) {
            if(user.getUserPassword().equals(userPassword)) {
                return user;
            }
        }
        return null;
    }


}
