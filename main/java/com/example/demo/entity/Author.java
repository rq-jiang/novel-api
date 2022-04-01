package com.example.demo.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Author implements Serializable {
    private Integer id;
    private String name;
    private String introduction;
    private Integer bookId;
    private Date createdAt;
    private Date updateAt;
}
