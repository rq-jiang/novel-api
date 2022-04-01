package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> findAll();
    List<Book> findByType(Integer id);
    List<Book> findByAuthor(Integer id);
    Book findById(Integer id);
}
