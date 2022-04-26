package com.example.demo.service;

import com.example.demo.entity.Author;

public interface AuthorService {

    Author findById(Integer id);

    Author findByName(String name);

    boolean insertAuthor(Author author);

}
