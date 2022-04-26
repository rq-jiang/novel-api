package com.example.demo.service;

import com.example.demo.entity.Volume;

import java.util.List;

public interface VolumeService {

    List<Volume> findAll();

    Volume findById(Integer id);
}
