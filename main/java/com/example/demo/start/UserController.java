package com.example.demo.start;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public int register(String nick_name,String email,String password) {
        System.out.println("获取的名字为"+nick_name+"邮箱"+email+"密码"+password);
        User user = new User();
        user.setNick_name(nick_name);
        user.setEmail(email);
        user.setCreated_at(new Date());
        user.setPassword(password);
        System.out.println(user);
        return userService.insertUser(user);
    }
    @RequestMapping("/login")
    public boolean toLogin(String email, String password, HttpSession session){
        System.out.println("email"+email+"password"+password);
        User user = userService.findByNickNameandPassWord(email);
        System.out.println(user);
        if(user != null && user.getPassword().equals(password)) {
            session.setAttribute("user",user);
            System.out.println("登录成功");
            return true;
        }
        System.out.println("登录失败");
        return false;
    }
}
