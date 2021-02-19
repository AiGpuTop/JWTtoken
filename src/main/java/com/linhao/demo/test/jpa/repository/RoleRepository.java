package com.linhao.demo.test.jpa.repository;

import com.linhao.demo.test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByRoleName(String name);

//    Role findByNameAndAge(String name, Integer age);
//
//    int findByAge(int age);
//
//    @Query(value = "select * from Role where name= ?1 ", nativeQuery = true)
//    Role findByNameFoQuery(String name);
}

