package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Chapter implements Serializable {
    private Integer id;
    private String name;
    private Integer volume_id;
    private Integer book_id;
    private String content;
    private Integer word_count;
    private Date created_at;
    private Date updated_at;
    private Integer order_num;
    private Integer status;
    private Book book;
}
