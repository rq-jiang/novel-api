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
    private Integer volumeId;
    private Integer bookId;
    private String content;
    private Integer wordCount;
    private Date createdAt;
    private Date updatedAt;
    private Integer orderNum;
}
