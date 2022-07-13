package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.UsercollectBook;
import com.example.demo.mapper.UsercollectBookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCollectBookServiceImpl implements UserCollectBookService{

    @Resource
    private UsercollectBookMapper usercollectBookMapper;

    @Override
    public boolean insert(Integer book_id, Integer user_id) {
        return usercollectBookMapper.insert(book_id,user_id);
    }

    @Override
    public boolean delete(Integer book_id, Integer user_id) {
        return usercollectBookMapper.delete(book_id,user_id);
    }

    @Override
    public List<Book> findByUserId(Integer user_id) {
        return usercollectBookMapper.findByUserId(user_id);
    }

    @Override
    public UsercollectBook findByUserAndBookId(Integer user_id,Integer book_user) {
        return usercollectBookMapper.findByUserAndBookId(user_id,book_user);
    }
}
