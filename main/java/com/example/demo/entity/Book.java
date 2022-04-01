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
    private Date createdAt;
    private Date updatedAt;
    private boolean isEnd;
    private Integer wordCount;
    private Integer typeId;
}
