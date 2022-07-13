package com.example.demo.start;

import com.example.demo.entity.Result;
import com.example.demo.entity.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class TypeController {

    @Resource
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
    public Result insertType(String type_name) {
        Type type1 = typeService.findByName(type_name);
        if(type1 != null) {
            return new Result(400,"已有该类型","");
        }
        Type type = new Type();
        type.setName(type_name);
        type.setCreated_at(new Date());
        type.setUpdated_at(new Date());
        typeService.insertType(type);
        return new Result(200,"新增成功","");
    }
    @RequestMapping("/findByName")
    public Type findByName(String name) {
        System.out.println("获取到的"+name);
        return typeService.findByName(name);
    }
}
