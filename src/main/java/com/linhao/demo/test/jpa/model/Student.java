package com.linhao.demo.test.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "student")
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    private String address;

    private String collage;

    private String sex;

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }

    public Student setCollage(String collage) {
        this.collage = collage;
        return this;
    }

    public Student setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
