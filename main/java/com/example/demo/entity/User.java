package com.example.demo.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;
    private String nick_name;
    private String password;
    private String email;
    private Integer book_id;
    private String cover_url;
    private int is_vip;
    private Date created_at;
    private Date updated_at;
    private Integer status;
    private List<Book> bookList;
}
