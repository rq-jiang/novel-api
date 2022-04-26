package com.example.demo.mapper;

import com.example.demo.entity.Book;
import com.example.demo.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> findAll();
    List<Book> findByType(Integer id);
    List<Book> findByAuthor(Integer id);
    List<Book> findByName(String name);
    List<Book> findBybookName(String name);
    Book findById(Integer id);
    boolean insertBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(Integer id);
}
