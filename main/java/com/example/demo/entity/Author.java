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
    private Date created_at;
    private Date updated_at;
    private String cover_url;
    private Integer status;
}
