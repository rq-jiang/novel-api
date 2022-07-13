package com.example.demo.service;

import com.example.demo.entity.LoginDTO;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    int insertUser(User user);

    User findById(Integer id);

    List<User> findAll();

    Result deleteUser(Integer id);


    Result login(LoginDTO loginDTO);

    User findByEmail(String email);

    Result updateUser(String newPassword,Integer id);
}
