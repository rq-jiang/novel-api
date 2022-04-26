package com.example.demo.mapper;

import com.example.demo.entity.Volume;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolumeMapper {

    List<Volume> findAll();

    Volume findById(Integer id);
}
