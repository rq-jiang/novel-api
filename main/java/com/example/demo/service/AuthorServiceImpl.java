package com.example.demo.service;

import com.example.demo.entity.Author;
import com.example.demo.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Resource
    private AuthorMapper authorMapper;

    @Override
    public Author findById(Integer id) {
        return authorMapper.findById(id);
    }

    @Override
    public Author findByName(String name) {
        return authorMapper.findByName(name);
    }

    @Override
    public boolean insertAuthor(Author author) {
        return authorMapper.insertAuthor(author);
    }
}
