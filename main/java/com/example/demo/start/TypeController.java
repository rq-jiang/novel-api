package com.example.demo.start;

import com.example.demo.entity.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/findAll")
    public List<Type> findAll(){
        return typeService.findAll();
    }

    @RequestMapping("/findById")
    public Type findById(Integer id){
        return typeService.findById(id);
    }

    @RequestMapping("/insertType")
    public boolean insertType(String type_name) {
//        Type type = typeService.findByName(type_name);
        Type type = new Type();
        type.setName(type_name);
        type.setCreated_at(new Date());
        type.setUpdated_at(new Date());
        return typeService.insertType(type);

    }
}
