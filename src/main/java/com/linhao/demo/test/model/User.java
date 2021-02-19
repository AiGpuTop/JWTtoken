package com.linhao.demo.test.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//用戶表
@Table(name = "user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
