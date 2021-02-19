package com.linhao.demo.test.jpa.repository;

import com.linhao.demo.test.jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);

    Student findByNameAndAge(String name, Integer age);

    int findByAge(int age);

    @Query(value = "select * from Student where name= ?1 ", nativeQuery = true)
    Student findByNameFoQuery(String name);
}

