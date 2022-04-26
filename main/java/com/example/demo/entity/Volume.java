package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors
public class Volume implements Serializable {
    private Integer id;
    private String name;
    private Integer book_id;
    private Date created_at;
    private Date updated_at;
    private Integer status;
}
