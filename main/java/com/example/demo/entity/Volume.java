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
    private Integer bookId;
    private Date createdAt;
    private Date updatedAt;
}
