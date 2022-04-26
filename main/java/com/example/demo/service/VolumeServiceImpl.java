package com.example.demo.service;

import com.example.demo.entity.Volume;
import com.example.demo.mapper.VolumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolumeServiceImpl implements VolumeService{

    @Autowired
    private VolumeMapper volumeMapper;

    @Override
    public List<Volume> findAll() {
        return volumeMapper.findAll();
    }

    @Override
    public Volume findById(Integer id) {
        return volumeMapper.findById(id);
    }
}
