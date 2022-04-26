package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Accessors(chain = true)
public class Book implements Serializable {
    private Integer id;
    private String name;
    private String introduction;
    private Date created_at;
    private Date updated_at;
    private Integer is_end;
    private Integer word_count;
    private Integer type_id;
    private Integer author_id;
    private String cover_url;
    private Type type;
    private Author author;
    private Integer status;
}
