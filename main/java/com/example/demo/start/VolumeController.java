package com.example.demo.start;

import com.example.demo.entity.Volume;
import com.example.demo.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/volume")
public class VolumeController {

    @Autowired
    private VolumeService volumeService;

    @RequestMapping("/findAll")
    public List<Volume> findAll(){
        return volumeService.findAll();
    }

    @RequestMapping("/findById")
    public Volume findById(Integer id){
        return volumeService.findById(id);
    }
}
