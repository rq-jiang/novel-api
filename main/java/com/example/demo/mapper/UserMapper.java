package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User findById(Integer id);

    User findByNickNameandPassWord(String email);

    boolean updateUser(User user);
}
