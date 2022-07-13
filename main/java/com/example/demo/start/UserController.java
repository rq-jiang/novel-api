package com.example.demo.start;


import com.example.demo.entity.LoginDTO;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.SendEmailService;
import com.example.demo.service.UserService;
import com.example.demo.service.VerifyCodeService;
import com.example.demo.util.VerifyCode;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Resource
    private VerifyCodeService verifyCodeService;

    @Resource
    private SendEmailService sendEmailService;

    private VerifyCode verifyCode;

//    private String user_cover = "/usr/tomcat/apache-tomcat-9.0.62/webapps/ROOT/images/user_cover/";
    private String user_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/user_cover/";


    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/sendRegisterCode")
    public Result sendRegisterCode(String email, HttpSession session) throws IOException{
        if(userService.findByEmail(email) != null){
            return new Result(400,"该邮箱已经注册","");
        }
        System.out.println(email);
        String code = verifyCode.runVerifyCode();
        System.out.println("验证码"+code);
        session.setAttribute("registerCode",code);
        return sendEmailService.sendSimpleMail(email,"验证码",code);
    }

    @PostMapping("/sendCode")
    public Result sendCode(String email, HttpSession session) throws IOException{
        System.out.println(email);
        String code = verifyCode.runVerifyCode();
        System.out.println("验证码"+code);
        session.setAttribute("code",code);
        return sendEmailService.sendSimpleMail(email,"验证码",code);
    }

    @PostMapping("/register")
    public Result register(String code,String nick_name,String email,String password,HttpSession session) {
        System.out.println("验证码"+code+"获取的名字为"+nick_name+"邮箱"+email+"密码"+password);
        System.out.println(session.getAttribute("registerCode"));
        if(code.equals(session.getAttribute("registerCode"))){
            User user = new User();
            user.setNick_name(nick_name);
            user.setEmail(email);
            user.setCreated_at(new Date());
            user.setPassword(password);
            user.setCover_url(user_cover);
            userService.insertUser(user);
            user_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/user_cover/";
            System.out.println(user);
            return new Result(200,"注册成功","");
        }
        return new Result(400,"注册失败","");
    }
    @RequestMapping("/login")
    public Result toLogin(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file) throws Exception {
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //拼接完整的文件保存路径
            String fileFullPath = user_cover + fileName;
            //删除现有的同名文件（如果有）。如果直接调用delete方法，会抛异常
            Files.deleteIfExists(Paths.get(fileFullPath));
            //保存文件
            file.transferTo(new File(fileFullPath));
            user_cover = "http://localhost:8080/images/user_cover/"+fileName;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "文件上传成功.";
    }
    @RequestMapping("/delete")
    public Result deleteUser(Integer id) {
        System.out.println("进入了方法");
        return userService.deleteUser(id);
    }

    @PostMapping("/updatedPassWord")
    public Result updatedPassword(String oldPassword,String newPassword,Integer id,HttpSession session,String code){
        System.out.println("获取的信息"+oldPassword+newPassword+id);
        System.out.println("获取的验证码"+session.getAttribute("code"));
        User user = userService.findById(id);
        System.out.println(user);
        if(!user.getPassword().equals(oldPassword)){
            return new Result(400,"原密码输入错误","");
        }
        if(code.equals(session.getAttribute("code"))){
            return userService.updateUser(newPassword,id);
        }
        return new Result(400,"验证码输入错误","");
    }

    @PostMapping("/updatedFindPassWord")
    public Result updatedFindPassWord(String password,String email,HttpSession session,String code){
        System.out.println("获取到的信息为："+password+email+code);
        User user = userService.findByEmail(email);
        System.out.println(user);
        if(user != null && user.getPassword().equals(password)){
            return new Result(400,"新密码不能与旧密码一样","");
        }
        if(user != null && session.getAttribute("code").equals(code)){
            return new Result(200,"密码已更新，请重新登录",userService.updateUser(password,user.getId()));
        }
        return new Result(400,"找回密码失败，请重新输入","");
    }
}
