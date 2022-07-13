package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class LoginVO implements Serializable {

    private Integer id;
    private String token;
    private User user;
}
