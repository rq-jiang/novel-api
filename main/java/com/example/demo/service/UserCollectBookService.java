package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.UsercollectBook;

import java.util.List;

public interface UserCollectBookService {

    boolean insert(Integer book_id,Integer user_id);

    boolean delete(Integer book_id,Integer user_id);

    List<Book> findByUserId(Integer user_id);

    UsercollectBook findByUserAndBookId(Integer book_id,Integer user_id);


}
