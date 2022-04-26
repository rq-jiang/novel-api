package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    int insertUser(User user);

    User findById(Integer id);

    User findByNickNameandPassWord(String email);

    boolean updateUser(User user);
}
