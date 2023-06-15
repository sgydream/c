package com.swxy.mapper;

import com.swxy.pojo.Userss;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    Userss findUserById(long userId);

    List<Userss> findUserByName(String userName);
}
