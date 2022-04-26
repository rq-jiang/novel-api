package com.example.demo.mapper;

import com.example.demo.entity.Author;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper {

    Author findById(Integer id);

    Author findByName(String name);

    boolean insertAuthor(Author author);
}
