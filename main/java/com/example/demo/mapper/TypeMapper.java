package com.example.demo.mapper;

import com.example.demo.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    List<Type> findAll();

    Type findById(Integer id);

    Type findByName(String name);

    boolean insertType(Type type);
}
