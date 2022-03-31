package com.example.demo.start;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/heiio")
    public String Hello(){
        return "HelloWorld";
    }
}
