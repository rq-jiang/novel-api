package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Chapter;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    List<Book> findByType(Integer id);
    List<Book> findByAuthor(Integer id);
    List<Book> findByName(String name);
    List<Book> findBybookName(String name);
    List<Book> findByUser(Integer id);
    Book findById(Integer id);
    boolean insertBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(Integer id);

}
