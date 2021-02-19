package com.linhao.demo.test.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//角色表
@Table(name = "role")
@Entity
@Data
public class Role {


    @Id
    @GeneratedValue
    private Long id;

    //    用户id
    private Long userId;
    //    角色名称
    private String roleName;


}
