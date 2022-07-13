package com.example.demo.service;

import com.example.demo.entity.LoginDTO;
import com.example.demo.entity.LoginVO;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public Result login(LoginDTO loginDTO) {
        if(StringUtils.isEmpty(loginDTO.getEmail())) {
            return new Result(400,"用户名不能为空","");
        }
        if(StringUtils.isEmpty(loginDTO.getPassword())){
            return new Result(400,"密码不能为空","");
        }
        User user = userMapper.findByemail(loginDTO.getEmail());
        if(user != null && user.getPassword().equals(loginDTO.getPassword())){
            LoginVO loginVO = new LoginVO();
            loginVO.setId(user.getId());
            loginVO.setToken(UUID.randomUUID().toString());
            loginVO.setUser(user);
            return new Result(200,"",loginVO);
        }
        return new Result(400,"登录失败,用户名或密码错误","");
    }

    @Override
    public Result updateUser(String newPassword,Integer id) {
        if(newPassword == null){
            return new Result(400,"新密码不能为空","");
        }
        User user = userMapper.findById(id);
        if(user == null){
            return new Result(400,"无法获取用户信息","");
        }
        userMapper.updateUser(newPassword,id);
        return new Result(200,"成功修改密码","");
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByemail(email);
    }

    @Override
    public Result deleteUser(Integer id) {
        if(userMapper.findById(id) != null) {
            userMapper.deleteUser(id);
            return new Result(200,"删除成功","");
        }
        return new Result(400,"删除失败","");
    }
}
