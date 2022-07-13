package com.example.demo.mapper;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    List<User> findAll();

    User findById(Integer id);

    User findByemail(String email);

    boolean updateUser(@Param("password") String newPassword,@Param("id") Integer id);

    boolean deleteUser(Integer id);
}
