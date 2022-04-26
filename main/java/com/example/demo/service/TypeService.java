package com.example.demo.service;

import com.example.demo.entity.Type;

import java.util.List;

public interface TypeService {

    List<Type> findAll();

    Type findById(Integer id);

    Type findByName(String name);

    boolean insertType(Type type);

}
