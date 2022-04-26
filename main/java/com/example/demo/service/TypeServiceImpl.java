package com.example.demo.service;

import com.example.demo.entity.Type;
import com.example.demo.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Resource
    private TypeMapper typeMapper;

    @Override
    public List<Type> findAll() {
        return typeMapper.findAll();
    }

    @Override
    public Type findById(Integer id) {
        return typeMapper.findById(id);
    }

    @Override
    public Type findByName(String name) {
        return typeMapper.findByName(name);
    }

    @Override
    public boolean insertType(Type type) {
        return typeMapper.insertType(type);
    }
}
