package com.linhao.demo.test.jpa.repository;

import com.linhao.demo.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

//    User findByNameAndAge(String name, Integer age);
//
//    int findByAge(int age);

    @Query(value = "select * from User where name= ?1 ", nativeQuery = true)
    User findByNameFoQuery(String name);
}

