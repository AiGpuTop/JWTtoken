package com.linhao.demo.test.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

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

    public User(String user, String password, Collection<? extends GrantedAuthority> authorities) {
    }

    public User() {
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
