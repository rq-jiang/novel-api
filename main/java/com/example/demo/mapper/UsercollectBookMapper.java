package com.example.demo.mapper;

import com.example.demo.entity.Book;
import com.example.demo.entity.UsercollectBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsercollectBookMapper {

    boolean insert(@Param("book_id") Integer book_id,@Param("user_id") Integer user_id);

    boolean delete(@Param("book_id") Integer book_id,@Param("user_id") Integer user_id);

    List<Book> findByUserId(Integer user_id);

    UsercollectBook findByUserAndBookId(@Param("book_id") Integer book_id, @Param("user_id") Integer user_id);

}
