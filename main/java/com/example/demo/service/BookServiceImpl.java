package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Chapter;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public List<Book> findByType(Integer id) {
        return bookMapper.findByType(id);
    }

    @Override
    public List<Book> findByAuthor(Integer id) {
        return bookMapper.findByAuthor(id);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookMapper.findByName(name);
    }

    @Override
    public List<Book> findBybookName(String name) {
        return bookMapper.findBybookName(name);
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Transactional
    @Override
    public boolean insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Transactional
    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Transactional
    @Override
    public boolean deleteBook(Integer id) {
        return bookMapper.deleteBook(id);
    }
}
