package com.example.demo.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UsercollectBook implements Serializable {

    private Integer id;

    private Integer book_id;
    private Integer user_id;

    private Book book;
//    private Author author;
//    private Type type;
}
